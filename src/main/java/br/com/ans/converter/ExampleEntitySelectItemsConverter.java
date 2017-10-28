package br.com.ans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.omnifaces.converter.SelectItemsConverter;

import br.com.ans.model.Perfil;

@FacesConverter("exampleEntitySelectItemsConverter")
public class ExampleEntitySelectItemsConverter extends SelectItemsConverter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Long id = (value instanceof Perfil) ? ((Perfil) value).getId() : null;
        return (id != null) ? String.valueOf(id) : null;
    }

}
