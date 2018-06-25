package controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Message;
import domain.Status;
import repos.MessageRepo;

@Controller
@RequestMapping("/message")
@PreAuthorize("hasAuthority('ADMIN')")
public class MessageController {
	@Autowired
	private MessageRepo messageRepo;
	
	@GetMapping
	public String messageList(Model model){
		model.addAttribute("messages", messageRepo.findAll());
		return "messageList";
	}
	
	@GetMapping("{message}")
    private String messageEditForm(@PathVariable Message message, Model model){
    	model.addAttribute("message", message);
    	model.addAttribute("statuses", Status.values());
    	return "messageEdit";	
    }
	
	@PostMapping
    public String messageSave(
    	@RequestParam String text,
    	@RequestParam Map<String, String> form,
    	@RequestParam("messageId") Message message){
    		
    		message.setText(text);
    		Set<String> statuses = Arrays.stream(Status.values())
    				.map(Status::name)
    				.collect(Collectors.toSet());
    		
    		message.getStatuses().clear();
    		
    		for(String key : form.keySet()){
    			if(statuses.contains(key)){
    				message.getStatuses().add(Status.valueOf(key));
    			}
    		}
    		messageRepo.save(message);
    		return "redirect:/message";
    	
    }
}
