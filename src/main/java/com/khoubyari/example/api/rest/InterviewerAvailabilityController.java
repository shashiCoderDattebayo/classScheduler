package com.khoubyari.example.api.rest;

import com.khoubyari.example.domain.InterviewSlot;
import com.khoubyari.example.domain.Interviewer;
import com.khoubyari.example.domain.InterviewerAvailabilityRequest;
import com.khoubyari.example.exception.DataFormatException;
import com.khoubyari.example.service.InterviewerAvailabilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/availability")
@Api(tags = {"interviewers"})
public class InterviewerAvailabilityController extends AbstractRestHandler {

    @Autowired
    private InterviewerAvailabilityService interviewerAvailabilityService;


    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all interviewers.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<InterviewSlot> getAllInterviewer(@ApiParam(value = "The page number (zero-based)", required = true)
                                        @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                        @ApiParam(value = "Tha page size", required = true)
                                        @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                        HttpServletRequest request, HttpServletResponse response) {
        return this.interviewerAvailabilityService.getAllInterviewers(page, size);
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a interviewer resource.", notes = "Returns the URL of the new resource in the Location header.")
    public List<InterviewSlot> createInterviewer(@RequestBody InterviewerAvailabilityRequest interviewerAvailabilityRequest,
                                 HttpServletRequest request, HttpServletResponse response) {
        return this.interviewerAvailabilityService.createInterviewSlots(interviewerAvailabilityRequest);
    }

}
