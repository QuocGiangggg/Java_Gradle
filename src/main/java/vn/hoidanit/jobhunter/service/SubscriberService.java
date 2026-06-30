package vn.hoidanit.jobhunter.service;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.repository.SkillRepository;
import vn.hoidanit.jobhunter.repository.SubscriberRepository;
import vn.hoidanit.jobhunter.domain.Skill;
import vn.hoidanit.jobhunter.domain.Subscriber;

@Service
public class SubscriberService {

    private final SubscriberRepository subscriberRepository;
    private final SkillRepository skillRepository;

    public SubscriberService(SubscriberRepository subscriberRepository, SkillRepository skillRepository) {
        this.subscriberRepository = subscriberRepository;
        this.skillRepository = skillRepository;
    }

    public boolean isExistByEmail(String email) {
        return this.subscriberRepository.existByEmail(email);
    }

    public Subscriber createSubscriber(Subscriber subs) {

        // check skill
        if (subs.getSkills() != null) {
            List<Long> reqSkills = subs.getSkills()
                    .stream().map(x -> x.getId())
                    .collect(Collectors.toList());

            List<Skill> dbSkill = this.skillRepository.findByIdIn(reqSkills);
            subs.setSkills(dbSkill);
        }

        return this.subscriberRepository.save(subs);
    }

    public Subscriber update(Subscriber subsDB, Subscriber subsRequest) {
        // check skill
        if (subsRequest.getSkills() != null) {
            List<Long> reqSkills = subsRequest.getSkills()
                    .stream().map(x -> x.getId())
                    .collect(Collectors.toList());

            List<Skill> dbSkill = this.skillRepository.findByIdIn(reqSkills);
            subsDB.setSkills(dbSkill);
        }

        return this.subscriberRepository.save(subsDB);
    }

    public Subscriber findById(long id) {
        return this.subscriberRepository.findById(id).orElse(null);
    }
}
