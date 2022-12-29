package com.cb.witfactory.data.classModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPumpDumy {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> head1 = new ArrayList<String>();
        head1.add("Estos Términos y Condiciones de Servicio (en adelante, “los términos”) rigen el uso y acceso a la aplicación, y cualquier información capturada por la misma.Las personas deben aceptar estos términos de uso, los cuales tienen un carácter obligatorio y vinculante, si usted no acepta los términos y condiciones deberá abstenerse de utilizar la aplicación.\n" +
                "\n" +
                "Mediante el uso de la aplicación, el usuario acepta la utilización de la información tratada por aquella, en los términos y condiciones establecidos en éstos. Asimismo, al hacer uso de estos servicios, el usuario accede a vincularse a estos Términos.\n" +
                "\n" +
                "El Usuario declara haber leído y aceptado los términos y condiciones. En caso que el usuario no esté de acuerdo con estos términos, deberá abstenerse de utilizar los servicios que proporciona la aplicación, sea que la desinstale o simplemente deje de usarla.\n");

        List<String> head2 = new ArrayList<String>();
        head2.add("Condiciones de Servicio (en adelante, “los términos”) rigen el uso y acceso a la aplicación, y cualquier información capturada por la misma.Las personas deben aceptar estos términos de uso, los cuales tienen un carácter obligatorio y vinculante, si usted no acepta los términos y condiciones deberá abstenerse de utilizar la aplicación.\n" +
                "\n" +
                "Mediante el uso de la aplicación, el usuario acepta la utilización de la información tratada por aquella, en los términos y condiciones establecidos en éstos. Asimismo, al hacer uso de estos servicios, el usuario accede a vincularse a estos Términos.\n" +
                "\n" +
                "El Usuario declara haber leído y aceptado los términos y condiciones. En caso que el usuario no esté de acuerdo con estos términos, deberá abstenerse de utilizar los servicios que proporciona la aplicación, sea que la desinstale o simplemente deje de usarla.\n");


        List<String> head3 = new ArrayList<String>();
        head3.add("Condiciones de Servicio (en adelante, “los términos”) rigen el uso y acceso a la aplicación, y cualquier información capturada por la misma.Las personas deben aceptar estos términos de uso, los cuales tienen un carácter obligatorio y vinculante, si usted no acepta los términos y condiciones deberá abstenerse de utilizar la aplicación.\n" +
                "\n" +
                "Mediante el uso de la aplicación, el usuario acepta la utilización de la información tratada por aquella, en los términos y condiciones establecidos en éstos. Asimismo, al hacer uso de estos servicios, el usuario accede a vincularse a estos Términos.\n" +
                "\n" +
                "El Usuario declara haber leído y aceptado los términos y condiciones. En caso que el usuario no esté de acuerdo con estos términos, deberá abstenerse de utilizar los servicios que proporciona la aplicación, sea que la desinstale o simplemente deje de usarla.\n");


      /*  List<String> science = new ArrayList<String>();
        science.add("Eggshell may act like sunblock");
        science.add("Brain hub predicts negative events");
        science.add("California hit by raging wildfires");
        science.add("Rosetta's comet seen in close-up");
        science.add("Secret of sandstone shapes revealed");*/

        expandableListDetail.put("4. CONDICIONES DE USO", head1);
        expandableListDetail.put("2. CONDICIONES DE ACCESO", head2);
        expandableListDetail.put("3. CONDICIONES DE ACCESO", head3);


     //   expandableListDetail.put("SCIENCE & ENVIRONMENT NEWS", science);
        return expandableListDetail;
    }
}
