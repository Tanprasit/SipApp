package org.example.servlet.sip;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.sip.SipApplicationSession;
import javax.servlet.sip.SipServlet;
import javax.servlet.sip.SipServletRequest;
import javax.servlet.sip.SipServletResponse;
import javax.servlet.sip.SipSession;

public class RegistryServlet extends SipServlet {
    
    private SipSession sipSession;
    private SipApplicationSession sipApplicationSession;
   
    @Override
    protected void doRegister(SipServletRequest req) throws ServletException,
            IOException {
        
        // Get the sessions.
        this.sipSession = req.getSession();
        this.sipApplicationSession = req.getApplicationSession();
        
        // print all attribute before and after
        System.out.println("\n sipSession before: "+ this.sipSession.getAttribute("sipSession"));
        this.sipSession.setAttribute("sipSession", 0);
        System.out.println("\n sipSession after: "+ this.sipSession.getAttribute("sipSession"));
        
        System.out.println("\n sipApplicationSession before: "+ this.sipApplicationSession.getAttribute("sipApplicationSession"));
        this.sipApplicationSession.setAttribute("sipApplicationSession", 0);
        System.out.println("\n sipApplicationSession after: "+ this.sipApplicationSession.getAttribute("sipApplicationSession"));
        
        System.out.println("\n sipServletMessage before: "+ req.getAttribute("sipServletMessage"));
        req.setAttribute("sipServletMessage", 0);
        System.out.println("\n sipServletMessage after: "+ req.getAttribute("sipServletMessage"));  
        
        SipServletResponse sipServletResponse = req.createResponse(200);
        sipServletResponse.send();
    }
}
