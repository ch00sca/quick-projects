package ch.helvetia.zepasweb.ui.helper;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

/**
 * Damit der Internet-Explorer den Documents Mode: IE9 Standards nimmt muss im HEADER der HTML-Seite als erster Tag dies
 * stehen: <meta http-equiv="X-UA-Compatible" content="IE=9" />
 *
 * Jedoch setzt JSF, Primefaces usw. noch weitere Tags vor diesem hin. Daher kann man diesen Tag nicht einfach so im
 * HTML-Template einfügen.
 *
 * Dieser PhaseListener setzt den oben erwähnten Tag in den HEADER.
 *
 * @author ch00ngr
 * @see http://stackoverflow.com/questions/6860779/how-to-make-a-meta-tag-the-first -one-in-the-head-section
 */
public class UACompatibleHeaderPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent arg0) {

    }

    @Override
    public void beforePhase(PhaseEvent event) {
        final FacesContext facesContext = event.getFacesContext();
        final HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.addHeader("X-UA-Compatible", "IE=9");
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}
