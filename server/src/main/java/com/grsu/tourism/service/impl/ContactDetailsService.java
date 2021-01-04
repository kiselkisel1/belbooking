package com.grsu.tourism.service.impl;

import com.google.common.collect.Lists;
import com.grsu.tourism.model.ContactDetails;
import com.grsu.tourism.repository.ContactDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
@Transactional
@AllArgsConstructor
public class ContactDetailsService {
    private final ContactDetailsRepository contactDetailsRepository;

    public Map<Integer, List<ContactDetails>> getAll() {
        List<ContactDetails> contactDetails = Lists.newArrayList(contactDetailsRepository.findAll());

        Map<Integer, List<ContactDetails>> contactDetailsById = new HashMap<>();
        if (!CollectionUtils.isEmpty(contactDetails)) {
            contactDetailsById = contactDetails.stream()
                    .collect(groupingBy(ContactDetails::getServiceId, mapping(row -> row, toList())));
        }
        return contactDetailsById;
    }

    public Map<Integer, List<ContactDetails>> getAllMapByServiceIds(Collection<Integer> serviceIds) {
        List<ContactDetails> contactDetails = contactDetailsRepository.findByServiceIdIn(serviceIds);

        Map<Integer, List<ContactDetails>> contactDeatilsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(contactDetails)) {
            contactDeatilsMap = contactDetails.stream()
                    .collect(groupingBy(ContactDetails::getServiceId, mapping(row -> row, toList())));
        }
        return contactDeatilsMap;
    }

    public ContactDetails save(ContactDetails contactDetails) {
        return this.contactDetailsRepository.save(contactDetails);
    }

    public void delete(Integer id) {
        contactDetailsRepository.deleteById(id);
    }
}