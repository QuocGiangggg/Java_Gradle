package vn.hoidanit.jobhunter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.Skill;
import vn.hoidanit.jobhunter.domain.Subscriber;
import vn.hoidanit.jobhunter.service.SubscriberService;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;
import vn.hoidanit.jobhunter.util.error.IdInvalidException;
import vn.hoidanit.jobhunter.util.SecurityUtil;

@RestController
@RequestMapping("/api/v1")
public class SubscriberController {

    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping("/subscribers")
    @ApiMessage("Create a subscriber")
    public ResponseEntity<Subscriber> create(@Valid @RequestBody Subscriber sub) throws IdInvalidException {
        // check email
        boolean isExist = this.subscriberService.isExistByEmail(sub.getEmail());

        if (isExist == true) {
            throw new IdInvalidException("Email " + sub.getEmail() + "đã tồn tại!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(this.subscriberService.createSubscriber(sub));
    }

    @PutMapping("/subscribers")
    @ApiMessage("Update a subscribers")
    public ResponseEntity<Subscriber> update(@RequestBody Subscriber subsRequest) throws IdInvalidException {
        // check id
        Subscriber subsDB = this.subscriberService.findById(subsRequest.getId());

        if (subsDB == null) {
            throw new IdInvalidException("Id" + subsDB.getId() + "Không tồn tại!");
        }

        return ResponseEntity.ok().body(this.subscriberService.update(subsDB, subsRequest));
    }

    @PostMapping("/subscribers/skills")
    @ApiMessage("Get subscriber skills")
    public ResponseEntity<Subscriber> getSubscriberSkills() throws IdInvalidException {
        String email = SecurityUtil.getCurrentUserLogin().isPresent() == true ? SecurityUtil.getCurrentUserLogin().get()
                : "";
        return ResponseEntity.ok().body(this.subscriberService.findByEmail(email));
    }
}
