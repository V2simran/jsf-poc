package com.poc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

@ViewScoped
@ManagedBean
public class DatatableBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pageNumber;
    
    private List<String> list;
    
    @PostConstruct
    public void init() {
    	prepareList();
    }

	private void prepareList() {
		list = new ArrayList<>();
    	for(int i = 1; i <= 25; i++) {
    		list.add("Test" + i);
    	}
	}

    public List<String> getList() {
    	if(list == null || list.isEmpty()) {
    		prepareList();
    	}
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

    public void deactivatePaginator() {
    	final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
    		    .findComponent("table");
    	pageNumber = dataTable.getPage();
    	dataTable.setPaginator(false);
        dataTable.setRows(0);
        dataTable.setFirst(0);
        PrimeFaces.current().executeScript("PF('widgetVar').getPaginator().setPage(0)");
    }

    public void activatePaginator() {
    	final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
    		    .findComponent("table");
    	 dataTable.setPaginator(true);
         dataTable.setRows(10);
         PrimeFaces.current().executeScript("PF('widgetVar').getPaginator().setPage(" + pageNumber + ")");
    }
    
}