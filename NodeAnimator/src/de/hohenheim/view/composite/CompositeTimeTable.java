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

import de.hohenheim.view.canvas.TimeTableControllerCanvas;

/**
 * This class create a new CompositeTimeTable with a Table and call the
 * Constructor for a new TimeTableControllerCanvas
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class CompositeTimeTable extends Composite {

	private ScrolledComposite scrollComposite;
	private GridLayout gridLayout;
	private static Table timeTableTable;

	/**
	 * A Constructor for a new CompositeTimeTable.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public CompositeTimeTable(Composite parent, int style) {
		super(parent, style);

		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);
		// this.setBackground(ColorConstants.black);

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

		setTimeTableTable(new Table(scrollComposite, SWT.FULL_SELECTION));

		TableColumn idTimeTable = new TableColumn(getTimeTableTable(),
				SWT.CENTER);
		TableColumn timeTableName = new TableColumn(getTimeTableTable(),
				SWT.CENTER);
		TableColumn startTime = new TableColumn(getTimeTableTable(), SWT.CENTER);
		TableColumn drivingDays = new TableColumn(getTimeTableTable(),
				SWT.CENTER);
		TableColumn startStation = new TableColumn(getTimeTableTable(),
				SWT.CENTER);
		TableColumn endStation = new TableColumn(getTimeTableTable(),
				SWT.CENTER);
		TableColumn middleStation = new TableColumn(getTimeTableTable(),
				SWT.CENTER);

		idTimeTable.setText("ID");
		timeTableName.setText("Fahrplanname");
		startTime.setText("Start Uhrzeit");
		drivingDays.setText("Fahrtage");
		startStation.setText("Startstation");
		endStation.setText("Endstation");
		middleStation.setText("Zwischenstation");

		idTimeTable.setWidth(70);
		timeTableName.setWidth(70);
		startTime.setWidth(80);
		drivingDays.setWidth(80);
		startStation.setWidth(80);
		endStation.setWidth(80);
		middleStation.setWidth(80);

		idTimeTable.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				TableItem[] items = timeTableTable.getItems();

				for (int i = 1; i < items.length; i++) {
					int value1 = Integer.parseInt(items[i].getText(0));
					for (int j = 0; j < i; j++) {
						int value2 = Integer.parseInt(items[j].getText(0));
						if (value1 <= value2) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4),
									items[i].getText(5), items[i].getText(6) };
							items[i].dispose();
							TableItem item = new TableItem(timeTableTable,
									SWT.NONE, j);
							item.setText(values);
							items = timeTableTable.getItems();
							break;
						}
					}
				}
			}
		});

		timeTableName.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				TableItem[] items = timeTableTable.getItems();
				Collator collator = Collator.getInstance(Locale.getDefault());
				for (int i = 1; i < items.length; i++) {
					String value1 = items[i].getText(1);
					for (int j = 0; j < i; j++) {
						String value2 = items[j].getText(1);
						if (collator.compare(value1, value2) < 0) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4),
									items[i].getText(5), items[i].getText(6) };
							items[i].dispose();
							TableItem item = new TableItem(timeTableTable,
									SWT.NONE, j);
							item.setText(values);
							items = timeTableTable.getItems();
							break;
						}
					}
				}
			}
		});

		startStation.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				TableItem[] items = timeTableTable.getItems();

				for (int i = 1; i < items.length; i++) {
					int value1 = Integer.parseInt(items[i].getText(4));
					for (int j = 0; j < i; j++) {
						int value2 = Integer.parseInt(items[j].getText(4));
						if (value1 < value2) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4),
									items[i].getText(5), items[i].getText(6) };
							items[i].dispose();
							TableItem item = new TableItem(timeTableTable,
									SWT.NONE, j);
							item.setText(values);
							items = timeTableTable.getItems();
							break;
						}
					}
				}
			}
		});

		endStation.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				TableItem[] items = timeTableTable.getItems();

				for (int i = 1; i < items.length; i++) {
					int value1 = Integer.parseInt(items[i].getText(5));
					for (int j = 0; j < i; j++) {
						int value2 = Integer.parseInt(items[j].getText(5));
						if (value1 < value2) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4),
									items[i].getText(5), items[i].getText(6)};
							items[i].dispose();
							TableItem item = new TableItem(timeTableTable,
									SWT.NONE, j);
							item.setText(values);
							items = timeTableTable.getItems();
							break;
						}
					}
				}
			}
		});

		getTimeTableTable().setLinesVisible(true);
		getTimeTableTable().setHeaderVisible(true);

		scrollComposite.setContent(getTimeTableTable());

		TimeTableControllerCanvas canvasControl = new TimeTableControllerCanvas(
				this, SWT.NONE);

		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasControl.setLayoutData(gridData);
	}

	/**
	 * Getter for the table TimeTable
	 * 
	 * @return timeTableTable
	 */
	public static Table getTimeTableTable() {
		return timeTableTable;
	}

	/**
	 * Setter for the table TimeTable
	 * 
	 * @param timeTableTable
	 */
	public static void setTimeTableTable(Table timeTableTable) {
		CompositeTimeTable.timeTableTable = timeTableTable;
	}

}
