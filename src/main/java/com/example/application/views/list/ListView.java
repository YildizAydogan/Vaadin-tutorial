package com.example.application.views.list;

import com.example.application.data.Contact;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.awt.*;

@PageTitle("Contacts | Vaadin CRM")
@Route(value = "")
public class ListView extends VerticalLayout {
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();

    public ListView() {


        addClassName("list-view");
        setSizeFull();

        configureGrid();

        add(
                getToolbar(),
                grid
        );

    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); // kullanıcı arama çubuğuna sürekli harfler girdiğinde, bu mod aktif olduğunda sistem bir süre bekler (genellikle birkaç saniye ya da kullanıcı yazmayı durdurduktan sonra) ve daha sonra olayı tetikler. Bu şekilde, kullanıcının girişi tamamlandıktan sonra bir kez sunucuya istek yapılır. Bu, gereksiz sunucu isteklerini azaltarak uygulamanın genel performansını artırır.


        Button addContactButton = new Button("Add Contact");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");

        return toolbar;

    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        grid.addColumn(contact-> contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact-> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col->col.setAutoWidth(true));


    }

}
