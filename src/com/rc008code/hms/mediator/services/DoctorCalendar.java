package com.rc008code.hms.mediator.services;

import com.rc008code.hms.dto.AppointmentDto;
import com.rc008code.hms.mediator.api.AppointmentColleague;
import com.rc008code.hms.mediator.api.AppointmentMediator;

import java.util.*;


public class DoctorCalendar implements AppointmentColleague {
    private AppointmentMediator mediator;
    private final Map<String, Set<Long>> reservations = new HashMap<>();

    @Override
    public void setMediator(AppointmentMediator mediator) {
        this.mediator = mediator;
    }

    public boolean isAvailable(String doctorId, Date slot) {
        Set<Long> set = reservations.getOrDefault(doctorId, Collections.emptySet());
        return !set.contains(slot.getTime());
    }

    public void reserve(AppointmentDto dto) {
        reservations.computeIfAbsent(dto.getDoctorId(), k -> new HashSet<>())
                .add(dto.getAppointmentDate().getTime());
    }

    public void release(String doctorId, Date slot) {
        Set<Long> set = reservations.get(doctorId);
        if (set != null) {
            set.remove(slot.getTime());
            if (set.isEmpty()) reservations.remove(doctorId);
        }
    }
}
