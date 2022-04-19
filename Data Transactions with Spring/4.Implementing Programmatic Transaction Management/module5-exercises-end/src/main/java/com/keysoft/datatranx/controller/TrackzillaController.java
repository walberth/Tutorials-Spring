package com.keysoft.datatranx.controller;

import com.keysoft.datatranx.model.Application;
import com.keysoft.datatranx.model.Release;
import com.keysoft.datatranx.model.Ticket;
import com.keysoft.datatranx.service.IApplicationService;
import com.keysoft.datatranx.service.IReleaseService;
import com.keysoft.datatranx.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tza")
public class TrackzillaController {
    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IReleaseService releaseService;

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> list = ticketService.getAllTickets();
        return new ResponseEntity<List<Ticket>>(list, HttpStatus.OK);
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
       List<Application> list = applicationService.getAllApplications();
       return new ResponseEntity<List<Application>>(list, HttpStatus.OK);
    }

    @PostMapping("/schedule")
    public ResponseEntity<Void> scheduleRelease(@RequestBody Release release) {
        releaseService.scheduleRelease(release);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
