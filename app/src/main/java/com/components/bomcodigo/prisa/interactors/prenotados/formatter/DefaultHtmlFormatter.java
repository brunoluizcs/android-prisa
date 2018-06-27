package com.components.bomcodigo.prisa.interactors.prenotados.formatter;


import android.support.annotation.NonNull;

public class DefaultHtmlFormatter implements HtmlFormatter {

    @Override
    public String removeLink(@NonNull String html) {
        return html.replaceAll("<a( href=\".+\")?>.+</a>","");
    }
}
