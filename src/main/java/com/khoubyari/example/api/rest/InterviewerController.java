package com.khoubyari.example.api.rest;

import com.khoubyari.example.domain.Interviewer;
import com.khoubyari.example.exception.DataFormatException;
import com.khoubyari.example.service.InterviewerService;
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

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/interviewers")
@Api(tags = {"interviewers"})
public class InterviewerController extends AbstractRestHandler {

    @Autowired
    private InterviewerService interviewerService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a interviewer resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createInterviewer(@RequestBody Interviewer interviewer,
                                 HttpServletRequest request, HttpServletResponse response) {
        Interviewer createdInterviewer = this.interviewerService.createInterviewer(interviewer);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdInterviewer.getId()).toString());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all interviewers.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<Interviewer> getAllInterviewer(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.interviewerService.getAllInterviewers(page, size);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single interviewer.", notes = "You have to provide a valid interviewer ID.")
    public
    @ResponseBody
    Interviewer getInterviewer(@ApiParam(value = "The ID of the interviewer.", required = true)
                             @PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Interviewer interviewer = this.interviewerService.getInterviewer(id);
        checkResourceFound(interviewer);
        //todo: http://goo.gl/6iNAkz
        return interviewer;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a interviewer resource.", notes = "You have to provide a valid interviewer ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateInterviewer(@ApiParam(value = "The ID of the existing interviewer resource.", required = true)
                                 @PathVariable("id") Long id, @RequestBody Interviewer interviewer,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.interviewerService.getInterviewer(id));
        if (id != interviewer.getId()) throw new DataFormatException("ID doesn't match!");
        this.interviewerService.updateInterviewer(interviewer);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a interviewer resource.", notes = "You have to provide a valid interviewer ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteInterviewer(@ApiParam(value = "The ID of the existing interviewer resource.", required = true)
                                 @PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.interviewerService.getInterviewer(id));
        this.interviewerService.deleteInterviewer(id);
    }
}
