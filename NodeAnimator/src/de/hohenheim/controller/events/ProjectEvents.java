package de.hohenheim.controller.events;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.canvas.AnimationControllerCanvas;
import de.hohenheim.view.composite.CompositeProject;
import de.hohenheim.view.dialouge.ProjectAddDialog;
import de.hohenheim.view.dialouge.ProjectEditDialog;

/**
 * The class ProjectEvents contain Methods to add, edit, delete, import and
 * export a Project.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class ProjectEvents {

	/**
	 * The method addLink add a new Item (TrainID and TimetaleID) to a Table in
	 * the ProjetAddDialog/ProjectEditDialog
	 * 
	 * @param add
	 *            - Give the information that a Link was add in the add Dialog
	 *            or in the edit Dialog
	 */
	public static void addLink(boolean add) {

		if (add == true) {

			TableItem item = new TableItem(ProjectAddDialog.linkTable, SWT.NONE);
			item.setText(0, ProjectAddDialog.comboChooseTrain.getText());
			item.setText(1, ProjectAddDialog.comboChooseTimeTable.getText());

		} else {

			TableItem item = new TableItem(ProjectEditDialog.linkTable,
					SWT.NONE);
			item.setText(0, ProjectEditDialog.comboChooseTrain.getText());
			item.setText(1, ProjectEditDialog.comboChooseTimeTable.getText());
		}
	}

	/**
	 * The Method removeLink remove a selected Item (TrainID and TimetaleID)
	 * from a Table in the ProjetAddDialog/ProjectEditDialog
	 * 
	 * @param add
	 *            - Give the information that a Link was remove in the add
	 *            Dialog or in the edit Dialog
	 */

	public static void removeLink(boolean add) {

		if (add == true) {

			ProjectAddDialog.linkTable.remove(ProjectAddDialog.linkTable
					.getSelectionIndex());

		} else {

			ProjectEditDialog.linkTable.remove(ProjectEditDialog.linkTable
					.getSelectionIndex());
		}

	}

	/**
	 * The Method addProject add a new project to the MainProjectlist (Data) and
	 * to the Table in the CompositeProject(View)
	 * 
	 */
	public static void addProject() {

		// Iniziales all needed Datas for a Project

		int id = Integer.parseInt(ProjectAddDialog.idText.getText());
		String name = ProjectAddDialog.nameText.getText();

		ArrayList<TrainData> trainList = new ArrayList<TrainData>();
		ArrayList<Timetable> timetableList = new ArrayList<Timetable>();

		for (int i = 0; i < ProjectAddDialog.linkTable.getItemCount(); i++) {

			for (int j = 0; j < Main.getTrainListAll().size(); j++) {
				TrainData td = Main.getTrainListAll().get(j);
				if (Integer.parseInt(ProjectAddDialog.linkTable.getItem(i)
						.getText(0)) == td.getID()) {
					trainList.add(td);
				}
			}

			for (int j = 0; j < Main.getTimetableListAll().size(); j++) {
				Timetable tt = Main.getTimetableListAll().get(j);
				if (Integer.parseInt(ProjectAddDialog.linkTable.getItem(i)
						.getText(1)) == tt.getId()) {
					timetableList.add(tt);
				}
			}

		}

		Project project = new Project(id, name, trainList, timetableList);

		// Add Project to the Mainlist

		Main.getProjectListAll().add(project);

		String trainsItem = "";

		for (int i = 0; i < trainList.size(); i++) {

			trainsItem = trainsItem + trainList.get(i).getID() + "; ";
		}

		String timetableItem = "";

		for (int i = 0; i < timetableList.size(); i++) {

			timetableItem = timetableItem + timetableList.get(i).getId() + "; ";

		}

		// Add Project to the Table

		TableItem item = new TableItem(CompositeProject.getProjectTable(),
				SWT.NONE);

		item.setText(new String[] { String.valueOf(id), name, trainsItem,
				timetableItem });

		ProjectAddDialog.dialog.close();

	}

	/**
	 * The Method editProject edit a existing Project in the Mainlist and in the
	 * Table.
	 * 
	 */
	public static void editProject() {

		ArrayList<TrainData> trainsID = new ArrayList<TrainData>();
		ArrayList<Timetable> timetableID = new ArrayList<Timetable>();

		TableItem[] rowData = CompositeProject.getProjectTable().getSelection();

		String idText = ProjectEditDialog.idText.getText();
		int id = Integer.parseInt(idText);

		int idCheck = Integer.parseInt(rowData[0].getText(0));

		for (Project p : Main.getProjectListAll()) {

			if (idCheck == p.getId()) {
				p.setId(id);
				p.setName(ProjectEditDialog.nameText.getText());

				for (int i = 0; i < ProjectEditDialog.linkTable.getItemCount(); i++) {

					for (int j = 0; j < Main.getTrainListAll().size(); j++) {
						TrainData td = Main.getTrainListAll().get(j);
						if (Integer.parseInt(ProjectEditDialog.linkTable
								.getItem(i).getText(0)) == td.getID()) {
							trainsID.add(td);
						}
					}

					for (int j = 0; j < Main.getTimetableListAll().size(); j++) {
						Timetable tt = Main.getTimetableListAll().get(j);
						if (Integer.parseInt(ProjectEditDialog.linkTable
								.getItem(i).getText(1)) == tt.getId()) {
							timetableID.add(tt);
						}
					}

				}
				p.setTimeTableProjectList(timetableID);
				p.setTraindataProjectList(trainsID);
			}
		}

		String trainsItem = "";

		for (int i = 0; i < trainsID.size(); i++) {

			trainsItem = trainsItem + trainsID.get(i).getID() + "; ";
		}

		String timetableItem = "";

		for (int i = 0; i < timetableID.size(); i++) {

			timetableItem = timetableItem + timetableID.get(i).getId() + "; ";

		}

		rowData[0].setText(0, idText);
		rowData[0].setText(1, ProjectEditDialog.nameText.getText());
		rowData[0].setText(2, trainsItem);
		rowData[0].setText(3, timetableItem);
		String[] projectIDs = new String[Main.getProjectListAll().size()];
		AnimationControllerCanvas.comboProjects
				.setItems(AnimationControllerCanvas.loadProjectIDs(projectIDs));

		ProjectEditDialog.dialog.close();

	}

	/**
	 * Method deletProject delete an exist Project from the Mainlist and from
	 * the Table in the CompositeProject
	 * 
	 */
	public static void deletProject() {

		int i = 0;

		TableItem[] rowData = CompositeProject.getProjectTable().getSelection();

		while (Integer.parseInt((rowData[0].getText(0))) != ((Main
				.getProjectListAll().get(i).getId()))) {
			i++;
		}

		Main.getProjectListAll().remove(i);

		CompositeProject.getProjectTable().remove(
				CompositeProject.getProjectTable().getSelectionIndices());

		String[] projectIDs = new String[Main.getProjectListAll().size()];
		AnimationControllerCanvas.comboProjects
				.setItems(AnimationControllerCanvas.loadProjectIDs(projectIDs));

	}

	/**
	 * Method importProject load a single project to the Mainprojectlist and to
	 * the Projecttable
	 * 
	 * @param p
	 *            - get as parameter an Project with ID, Name , Trainlist and
	 *            Timetablelist
	 */
	public static void importProject(Project p) {

		if (idCheck(p.getId())) {

			Main.getProjectListAll().add(p);

			String trainsItem = "";

			for (int i = 0; i < p.getTraindataProjectList().size(); i++) {
				TrainEvents.importTrain(p.getTraindataProjectList().get(i));
				trainsItem = trainsItem
						+ p.getTraindataProjectList().get(i).getID() + "; ";
			}

			String timetableItem = "";

			for (int i = 0; i < p.getTimeTableProjectList().size(); i++) {
				TimeTableEvents.importTimetable(p.getTimeTableProjectList()
						.get(i));
				timetableItem = timetableItem
						+ p.getTimeTableProjectList().get(i).getId() + "; ";

			}

			// Add Project to the Table

			TableItem item = new TableItem(CompositeProject.getProjectTable(),
					SWT.NONE);

			item.setText(new String[] { String.valueOf(p.getId()), p.getName(),
					trainsItem, timetableItem });
			String[] projectIDs = new String[Main.getProjectListAll().size()];
			AnimationControllerCanvas.comboProjects
					.setItems(AnimationControllerCanvas
							.loadProjectIDs(projectIDs));
		} else {
			MessageBox messageBox = new MessageBox(Main.getMainShell(),
					SWT.ERROR | SWT.OK);
			messageBox
					.setMessage("Das zu ladene Projekt konnte nicht geladen werden !\n"
							+ "Da das zu ladene Projekt die selbe ID wie ein bereits \n"
							+ "existierendes Projekt hat!");
			messageBox.open();
		}

	}

	/**
	 * The Methode idCheck control that the id which is given aas parameter not
	 * exist in the MainProjekt list
	 * 
	 * @param id
	 *            - Project id from the loading xml File
	 * @return boolean - return true if the id not exist else false
	 */
	private static boolean idCheck(int id) {

		boolean check = true;

		for (int j = 0; j < Main.getProjectListAll().size(); j++) {

			if (Main.getProjectListAll().get(j).getId() == id) {
				check = false;
			}

		}

		return check;

	}
	
	/**
	 * 
	 * Methode importAllProjects load one or more then one Projects into the Program when it starts
	 * 
	 */
	public static void importAllProjects(){
		
		for(Project p : Main.getProjectListAll()){
		
			String trainsItem = "";

			for (int i = 0; i < p.getTraindataProjectList().size(); i++) {
				
				trainsItem = trainsItem
						+ p.getTraindataProjectList().get(i).getID() + "; ";
			}

			String timetableItem = "";

			for (int i = 0; i < p.getTimeTableProjectList().size(); i++) {
				
				
				timetableItem = timetableItem
						+ p.getTimeTableProjectList().get(i).getId() + "; ";
				

			}
			
			TableItem item = new TableItem(CompositeProject.getProjectTable(),
					SWT.NONE);
			
			item.setText(new String[] { String.valueOf(p.getId()), p.getName(),
					trainsItem, timetableItem });
			
		}
		
		String[] projectIDs = new String[Main.getProjectListAll().size()];
		AnimationControllerCanvas.comboProjects
				.setItems(AnimationControllerCanvas
						.loadProjectIDs(projectIDs));
	}
}
