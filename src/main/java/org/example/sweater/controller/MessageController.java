package org.example.sweater.controller;

import org.example.sweater.domain.Message;
import org.example.sweater.domain.User;
import org.example.sweater.domain.dto.MessageDto;
import org.example.sweater.repos.MessageRepo;
import org.example.sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class MessageController {


    private final MessageRepo messageRepo;
    private final MessageService messageService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public MessageController(MessageRepo messageRepo, MessageService messageService) {
        this.messageRepo = messageRepo;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @AuthenticationPrincipal User user) {

        Page<MessageDto> page = messageService.messageList(pageable, filter, user);


        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @Valid Message message,
                      BindingResult bindingResult,
                      @RequestParam("file") MultipartFile file,
                      Model model,
                      @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) throws IOException {
        message.setAuthor(user);

        Page<MessageDto> page = messageService.messageList(pageable, "", user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);

        } else {
            saveFile(message, file);
            model.addAttribute("message", null);

            messageRepo.save(message);
        }
        model.addAttribute("url", "/main");
        model.addAttribute("page", page);

//        model.addAttribute("messages", messageRepo.findAll());
        return "/main";
    }

    private void saveFile(@Valid Message message, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuid = UUID.randomUUID().toString();
            String resultFilename = uuid + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            message.setFilename(resultFilename);
        }
    }

    @GetMapping("/user-messages/{author}")
    public String userMessages(@AuthenticationPrincipal User currentUser,
                               @PathVariable User author,
                               Model model,
                               @RequestParam(required = false) Message message,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<MessageDto> page = messageService.messageListForUser(pageable, currentUser, author);

        model.addAttribute("userChannel", author);
        model.addAttribute("subscriptionsCount", author.getSubscriptions().size());
        model.addAttribute("subscribersCount", author.getSubscribers().size());
        model.addAttribute("isSubscriber", author.getSubscribers().contains(currentUser));
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(author));
        model.addAttribute("url", "/user-messages/" + author.getId());
        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(@AuthenticationPrincipal User currentUser,
                                @PathVariable Integer user,
                                @RequestParam("id") Message message,
                                @RequestParam("text") String text,
                                @RequestParam(value = "tag", required = false) String tag,
                                @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        if (message.getAuthor().equals(currentUser)) {

            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }
            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }
            saveFile(message, file);
            messageRepo.save(message);
        }

        return "redirect:/user-messages/" + user;
    }


    @GetMapping("/messages/{message}/like")
    public String like(@AuthenticationPrincipal User currentUser,
                       @PathVariable Message message,
                       RedirectAttributes attributes,
                       @RequestHeader(required = false) String referer) {

        Set<User> likes = message.getLikes();

        if (likes.contains(currentUser)) {
            likes.remove(currentUser);
        } else {
            likes.add(currentUser);
        }

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(referer).build();

        uriComponents.getQueryParams()
                .forEach(attributes::addAttribute);

        return "redirect:" + uriComponents.getPath();
    }


}