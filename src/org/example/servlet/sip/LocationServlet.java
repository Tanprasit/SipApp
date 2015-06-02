package org.example.servlet.sip;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.sip.Proxy;
import javax.servlet.sip.SipApplicationSession;
import javax.servlet.sip.SipServlet;
import javax.servlet.sip.SipServletMessage;
import javax.servlet.sip.SipServletRequest;
import javax.servlet.sip.SipServletResponse;
import javax.servlet.sip.SipSession;

public class LocationServlet extends SipServlet {
    
    private SipSession sipSession;
    private SipApplicationSession sipApplicationSession;
    
    @Override
    protected void doInvite(SipServletRequest req) throws ServletException,
            IOException {
        System.out.println("\n\n\n########################### INVITE RESCEIVED ###########################\n\n");
        
        // Get the sessions.
        this.sipSession = req.getSession();
        this.sipApplicationSession = req.getApplicationSession();
        
        // print all attribute before and after
        System.out.println("\n sipSession before: "+ this.sipSession.getAttribute("sipSession"));
        this.sipSession.setAttribute("sipSession", 0);
        System.out.println("\n sipSession after: "+ this.sipSession.getAttribute("sipSession") + "\n");
        
        System.out.println("\n sipApplicationSession before: "+ this.sipApplicationSession.getAttribute("sipApplicationSession"));
        this.sipApplicationSession.setAttribute("sipApplicationSession", 0);
        System.out.println("\n sipApplicationSession after: "+ this.sipApplicationSession.getAttribute("sipApplicationSession" + "\n"));
        
        System.out.println("\n sipServletMessage before: "+ req.getAttribute("sipServletMessage"));
        req.setAttribute("sipServletMessage", 0);
        System.out.println("\n sipServletMessage after: "+ req.getAttribute("sipServletMessage") + "\n");  
        
        // Proxy request off.
        Proxy proxy = req.getProxy();
        proxy.proxyTo(req.getRequestURI());
    }
    
    @Override
    protected void doResponse(SipServletResponse resp) throws ServletException,
            IOException {
        
        System.out.println("\n\n\n########################### RESPONSE RESCEIVED ###########################\n\n");
        
        this.sipSession = resp.getSession();
        this.sipApplicationSession = resp.getApplicationSession();
        
        Integer sipMessageInt = (Integer) resp.getAttribute("sipServletMessage");
        
        // Check if Servlet message contains attributes
        if (resp.getAttributeNames().hasMoreElements()) {
            System.out.println("\n sipServletMessage before: "+ sipMessageInt);  
            System.out.println("\n sipServletMessage after: "+ ++sipMessageInt);  
        } else {
            System.out.println("\n sipServletMessage before: null");
            sipMessageInt = 0;
            System.out.println("\n sipServletMessage after: "+ 0 +"\n"); 
        }
        
        // Check if Servlet application session contains attributes
        Integer appSessionInt = (Integer) this.sipApplicationSession.getAttribute("sipApplicationSession");
        System.out.println("\n sipApplicationSession before: "+ appSessionInt);  
        System.out.println("\n sipApplicationSession after: "+ ++appSessionInt + "\n");  
        
        // Check if Servlet session contains attributes.
        Integer sessionInt = (Integer) this.sipSession.getAttribute("sipSession");
        System.out.println("\n sipSession before: " + sessionInt);
        System.out.println("\n sipSession after: " + ++sessionInt + "\n");
        
        // Store it back in the session.
        this.sipSession.setAttribute("sipSession", sessionInt);
        this.sipApplicationSession.setAttribute("sipApplicationSession", appSessionInt);
        resp.setAttribute("sipServletMessage", sipMessageInt);
    }
}
