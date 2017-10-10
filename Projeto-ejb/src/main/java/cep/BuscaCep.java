package cep;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Stateless
public class BuscaCep {

    public Webservicecep getCep(final String cep) {
        JAXBContext jc;
        Webservicecep retCep = null;
        try {
            jc = JAXBContext.newInstance(Webservicecep.class);
            Unmarshaller u = jc.createUnmarshaller();
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
//            URL url = new URL("http://correiosapi.apphb.com/cep/" + cep);
            retCep = (Webservicecep) u.unmarshal(url);
        } catch (JAXBException | MalformedURLException ex) {
            Logger.getLogger(BuscaCep.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retCep;

    }

}
