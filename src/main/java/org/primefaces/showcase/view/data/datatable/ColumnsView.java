package org.primefaces.showcase.view.data.datatable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.component.columns.Columns;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.service.CarService;

@ManagedBean(name = "dtColumnsView")
@ViewScoped
public class ColumnsView implements Serializable {

	private final static List<String> VALID_COLUMN_KEYS = Arrays.asList("id", "brand", "year", "color", "price");

	private String columnTemplate = "id brand year";

	private List<ColumnModel> columns;

	private List<Car> cars;

	private List<Car> filteredCars;

	@ManagedProperty("#{carService}")
	private CarService service;

	@PostConstruct
	public void init() {
		cars = service.createCars(50);

		createDynamicColumns();

		createProgramaticGrid();
	}

	public static ValueExpression createValueExpression(String valueExpression, Class<?> valueType) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getApplication().getExpressionFactory().createValueExpression(facesContext.getELContext(),
				valueExpression, valueType);
	}

	private void createProgramaticGrid() {
		DataTable dataTable = createDataTable();

		dataTable.setId("progCars");
		dataTable.setVar("progCar");
		dataTable.setValueExpression("value", createValueExpression("#{dtColumnsView.cars}", List.class));

		dataTable.setPaginator(true);
		dataTable.setRows(10);
		Columns columns = createColumn();
		ValueExpression columnValues = createValueExpression("#{dtColumnsView.columns}", List.class);
		columns.setValueExpression("value", columnValues);
		columns.setVar("column");

		OutputLabel headerValue = createOutputLabel();
		headerValue.setValueExpression("value", createValueExpression("#{column.header}", String.class));
		columns.getFacets().put("header", headerValue);

		OutputLabel value = createOutputLabel();
		value.setValueExpression("value", createValueExpression("#{progCar[column.property]}", String.class));

		columns.getChildren().add(value);

		dataTable.getChildren().add(columns);

		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = context.getViewRoot();
		viewRoot.findComponent("form:mainGridBody").getChildren().add(dataTable);

	}

	private OutputLabel createOutputLabel() {
		return (OutputLabel) createComponent(OutputLabel.COMPONENT_TYPE);
		// return new OutputLabel();
	}

	private Columns createColumn() {
		return (Columns) createComponent(Columns.COMPONENT_TYPE);
		// return new Columns();
	}

	private DataTable createDataTable() {
		return (DataTable) createComponent(DataTable.COMPONENT_TYPE);
		// return new DataTable();
	}

	private UIComponent createComponent(String componentType) {
		return FacesContext.getCurrentInstance().getApplication().createComponent(componentType);
	}

	public List<Car> getCars() {
		return cars;
	}

	public List<Car> getFilteredCars() {
		return filteredCars;
	}

	public void setFilteredCars(List<Car> filteredCars) {
		this.filteredCars = filteredCars;
	}

	public void setService(CarService service) {
		this.service = service;
	}

	public String getColumnTemplate() {
		return columnTemplate;
	}

	public void setColumnTemplate(String columnTemplate) {
		this.columnTemplate = columnTemplate;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	private void createDynamicColumns() {
		String[] columnKeys = columnTemplate.split(" ");
		columns = new ArrayList<ColumnModel>();

		for (String columnKey : columnKeys) {
			String key = columnKey.trim();

			if (VALID_COLUMN_KEYS.contains(key)) {
				columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
			}
		}
	}

	public void updateColumns() {
		// reset table state
		UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:cars");
		table.setValueExpression("sortBy", null);

		// update columns
		createDynamicColumns();
	}

	static public class ColumnModel implements Serializable {

		private String header;
		private String property;

		public ColumnModel(String header, String property) {
			this.header = header;
			this.property = property;
		}

		public String getHeader() {
			return header;
		}

		public String getProperty() {
			return property;
		}
	}
}