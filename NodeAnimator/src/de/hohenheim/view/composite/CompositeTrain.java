package de.hohenheim.view.composite;

import java.text.Collator;
import java.util.Locale;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import de.hohenheim.view.canvas.TrainControllerCanvas;

/**
 * This class create a new CompositeTrain with a Table and call the Constructor
 * for a new TrainControllerCanvas
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofsäß
 * 
 * @version 1.0
 */
public class CompositeTrain extends Composite {

	private ScrolledComposite scrollComposite;
	private GridLayout gridLayout;
	private static Table trainTable;

	/**
	 * A Constructor for a new TrainComposite
	 * 
	 * @param parent
	 *            - where the Composite is in
	 * @param style
	 *            - witch style the Composite should have
	 */
	public CompositeTrain(Composite parent, int style) {
		super(parent, style);
		createConten();
	}

	/**
	 * Create the Content of the new Train Composite (the TrainTable, the
	 * TrainControllersCanvas
	 * 
	 */
	private void createConten() {

		// Set the GridLayout

		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);

		// Set a new ScrollComposite

		scrollComposite = new ScrolledComposite(this, SWT.H_SCROLL
				| SWT.V_SCROLL);

		scrollComposite.setVisible(true);
		scrollComposite.setExpandHorizontal(true);
		scrollComposite.setExpandVertical(true);
		scrollComposite.setBackground(ColorConstants.blue);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		scrollComposite.setLayoutData(gridData);

		// Set the TrainTable

		trainTable = new Table(scrollComposite, SWT.FULL_SELECTION);
		TableColumn idTrain = new TableColumn(getTrainTable(), SWT.CENTER);
		TableColumn typOfTrain = new TableColumn(getTrainTable(), SWT.CENTER);
		TableColumn maxSpeed = new TableColumn(getTrainTable(), SWT.CENTER);
		TableColumn priority = new TableColumn(getTrainTable(), SWT.CENTER);
		TableColumn ladung = new TableColumn(getTrainTable(), SWT.CENTER);

		idTrain.setText("ID");
		typOfTrain.setText("ZugTyp");
		maxSpeed.setText("Höchstgeschwindigkeit");
		priority.setText("Priorität");
		ladung.setText("Ladung");

		idTrain.setWidth(70);
		typOfTrain.setWidth(70);
		maxSpeed.setWidth(80);
		priority.setWidth(70);
		ladung.setWidth(80);

		// Set the listener for Sort a TrainTable when clicking on the Header of
		// a Column

		idTrain.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				TableItem[] items = trainTable.getItems();

				for (int i = 1; i < items.length; i++) {
					int value1 = Integer.parseInt(items[i].getText(0));
					for (int j = 0; j < i; j++) {
						int value2 = Integer.parseInt(items[j].getText(0));
						if (value1 <= value2) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4) };
							items[i].dispose();
							TableItem item = new TableItem(trainTable,
									SWT.NONE, j);
							item.setText(values);
							items = trainTable.getItems();
							break;
						}
					}
				}
			}
		});

		typOfTrain.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				TableItem[] items = trainTable.getItems();
				Collator collator = Collator.getInstance(Locale.getDefault());
				for (int i = 1; i < items.length; i++) {
					String value1 = items[i].getText(1);
					for (int j = 0; j < i; j++) {
						String value2 = items[j].getText(1);
						if (collator.compare(value1, value2) < 0) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4) };
							items[i].dispose();
							TableItem item = new TableItem(trainTable,
									SWT.NONE, j);
							item.setText(values);
							items = trainTable.getItems();
							break;
						}
					}
				}
			}
		});

		maxSpeed.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				TableItem[] items = trainTable.getItems();

				for (int i = 1; i < items.length; i++) {
					int value1 = Integer.parseInt(items[i].getText(2));
					for (int j = 0; j < i; j++) {
						int value2 = Integer.parseInt(items[j].getText(2));
						if (value1 < value2) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4) };
							items[i].dispose();
							TableItem item = new TableItem(trainTable,
									SWT.NONE, j);
							item.setText(values);
							items = trainTable.getItems();
							break;
						}
					}
				}
			}
		});

		priority.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {

				TableItem[] items = trainTable.getItems();

				for (int i = 1; i < items.length; i++) {
					int value1;
					if (items[i].getText(3).equalsIgnoreCase("Sehr wichtig")) {
						value1 = 0;
					} else if (items[i].getText(3).equalsIgnoreCase("Wichtig")) {
						value1 = 1;
					} else if (items[i].getText(3).equalsIgnoreCase("Normal")) {
						value1 = 2;
					} else {
						value1 = 3;
					}

					for (int j = 0; j < i; j++) {
						int value2;
						if (items[j].getText(3)
								.equalsIgnoreCase("Sehr wichtig")) {
							value2 = 0;
						} else if (items[j].getText(3).equalsIgnoreCase(
								"Wichtig")) {
							value2 = 1;
						} else if (items[j].getText(3).equalsIgnoreCase(
								"Normal")) {
							value2 = 2;
						} else {
							value2 = 3;
						}

						if (value1 < value2) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4) };
							items[i].dispose();
							TableItem item = new TableItem(trainTable,
									SWT.NONE, j);
							item.setText(values);
							items = trainTable.getItems();
							break;
						}
					}
				}
			}
		});

		ladung.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				TableItem[] items = trainTable.getItems();
				Collator collator = Collator.getInstance(Locale.getDefault());
				for (int i = 1; i < items.length; i++) {
					String value1 = items[i].getText(4);
					for (int j = 0; j < i; j++) {
						String value2 = items[j].getText(4);
						if (collator.compare(value1, value2) < 0) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4) };
							items[i].dispose();
							TableItem item = new TableItem(trainTable,
									SWT.NONE, j);
							item.setText(values);
							items = trainTable.getItems();
							break;
						}
					}
				}
			}
		});

		// Set the visibility of Lines and Header

		getTrainTable().setLinesVisible(true);
		getTrainTable().setHeaderVisible(true);

		scrollComposite.setContent(getTrainTable());

		// Set a new TrainControllerCanvas

		TrainControllerCanvas canvasControl = new TrainControllerCanvas(this,
				SWT.NONE);

		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasControl.setLayoutData(gridData);

	}

	/**
	 * Getter for TrainTable
	 * 
	 * @return Table - return the Table from the Train Composite with the train
	 *         Data
	 */
	public static Table getTrainTable() {
		return trainTable;
	}

}
