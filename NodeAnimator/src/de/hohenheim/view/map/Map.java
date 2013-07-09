package de.hohenheim.view.map;

import org.eclipse.swt.widgets.Canvas;

import de.hohenheim.modell.Path;
import de.hohenheim.modell.RouteSectionMarker;

/**
 * The class Map contains all the path numbers of the node map.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class Map {

	/**
	 * The method NodeMap contains the different between stations in the
	 * animation.
	 * 
	 * @param number
	 *            - the node numbers in the map
	 * @param args
	 * @return map - the node map
	 */
	public static NodeMap createMap(Canvas c, int number) {

		NodeMap map = new NodeMap(c);

		RouteSectionMarker m_1 = new RouteSectionMarker(map, "1", 75, 40);
		RouteSectionMarker m_2 = new RouteSectionMarker(map, "2", 25, 100);
		RouteSectionMarker m_3 = new RouteSectionMarker(map, "3", 25, 200);
		RouteSectionMarker m_4 = new RouteSectionMarker(map, "4", 25, 275);
		RouteSectionMarker m_5 = new RouteSectionMarker(map, "5", 25, 350);
		RouteSectionMarker m_6 = new RouteSectionMarker(map, "6", 75, 400);
		RouteSectionMarker m_7 = new RouteSectionMarker(map, "7", 150, 400);
		RouteSectionMarker m_8 = new RouteSectionMarker(map, "8", 400, 400);
		RouteSectionMarker m_9 = new RouteSectionMarker(map, "9", 200, 375);
		RouteSectionMarker m_10 = new RouteSectionMarker(map, "10", 375, 375);
		RouteSectionMarker m_11 = new RouteSectionMarker(map, "11", 175, 425);
		RouteSectionMarker m_12 = new RouteSectionMarker(map, "12", 200, 450);
		RouteSectionMarker m_13 = new RouteSectionMarker(map, "13", 425, 450);

		RouteSectionMarker m_14 = new RouteSectionMarker(map, "14", 150, 450);
		RouteSectionMarker m_15 = new RouteSectionMarker(map, "15", 175, 475);
		RouteSectionMarker m_16 = new RouteSectionMarker(map, "16", 400, 475);
		RouteSectionMarker m_17 = new RouteSectionMarker(map, "17", 460, 450);
		RouteSectionMarker m_18 = new RouteSectionMarker(map, "18", 480, 425);
		RouteSectionMarker m_19 = new RouteSectionMarker(map, "19", 500, 400);
		RouteSectionMarker m_20 = new RouteSectionMarker(map, "20", 450, 375);
		RouteSectionMarker m_21 = new RouteSectionMarker(map, "21", 515, 425);
		RouteSectionMarker m_22 = new RouteSectionMarker(map, "22", 535, 400);
		RouteSectionMarker m_23 = new RouteSectionMarker(map, "23", 570, 350);
		RouteSectionMarker m_24 = new RouteSectionMarker(map, "24", 540, 350);
		RouteSectionMarker m_25 = new RouteSectionMarker(map, "25", 540, 275);
		RouteSectionMarker m_26 = new RouteSectionMarker(map, "26", 540, 200);
		RouteSectionMarker m_27 = new RouteSectionMarker(map, "27", 570, 275);
		RouteSectionMarker m_28 = new RouteSectionMarker(map, "28", 570, 200);
		RouteSectionMarker m_29 = new RouteSectionMarker(map, "29", 100, 450);
		RouteSectionMarker m_30 = new RouteSectionMarker(map, "30", 100, 475);
		RouteSectionMarker m_31 = new RouteSectionMarker(map, "31", 570, 140);
		RouteSectionMarker m_32 = new RouteSectionMarker(map, "32", 540, 140);
		RouteSectionMarker m_33 = new RouteSectionMarker(map, "33", 500, 100);
		RouteSectionMarker m_34 = new RouteSectionMarker(map, "34", 500, 70);
		RouteSectionMarker m_35 = new RouteSectionMarker(map, "35", 450, 100);
		RouteSectionMarker m_36 = new RouteSectionMarker(map, "36", 420, 125);
		RouteSectionMarker m_37 = new RouteSectionMarker(map, "37", 420, 100);
		RouteSectionMarker m_38 = new RouteSectionMarker(map, "38", 420, 70);
		RouteSectionMarker m_39 = new RouteSectionMarker(map, "39", 200, 70);
		RouteSectionMarker m_40 = new RouteSectionMarker(map, "40", 225, 100);
		RouteSectionMarker m_41 = new RouteSectionMarker(map, "41", 450, 70);
		RouteSectionMarker m_42 = new RouteSectionMarker(map, "42", 420, 40);

		RouteSectionMarker m_43 = new RouteSectionMarker(map, "43", 175, 40);

		RouteSectionMarker m_44 = new RouteSectionMarker(map, "44", 250, 125);
		RouteSectionMarker m_45 = new RouteSectionMarker(map, "45", 175, 100);
		RouteSectionMarker m_46 = new RouteSectionMarker(map, "46", 450, 475);
		RouteSectionMarker m_47 = new RouteSectionMarker(map, "47", 125, 375);
		RouteSectionMarker m_48 = new RouteSectionMarker(map, "48", 100, 350);
		RouteSectionMarker m_49 = new RouteSectionMarker(map, "49", 100, 300);

		RouteSectionMarker m_50 = new RouteSectionMarker(map, "50", 125, 275);
		RouteSectionMarker m_51 = new RouteSectionMarker(map, "51", 125, 225);
		RouteSectionMarker m_52 = new RouteSectionMarker(map, "52", 100, 200);
		RouteSectionMarker m_53 = new RouteSectionMarker(map, "53", 75, 175);
		RouteSectionMarker m_54 = new RouteSectionMarker(map, "54", 100, 125);
		RouteSectionMarker m_55 = new RouteSectionMarker(map, "55", 75, 125);
		RouteSectionMarker m_56 = new RouteSectionMarker(map, "56", 175, 400);

		new Path(map, m_55, m_53, 100);
		new Path(map, m_53, m_52, 100);
		new Path(map, m_54, m_52, 150);
		new Path(map, m_52, m_49, 200);
		new Path(map, m_49, m_48, 250);
		new Path(map, m_48, m_47, 300);
		new Path(map, m_47, m_9, 300);
		new Path(map, m_49, m_50, 100);
		new Path(map, m_50, m_51, 150);
		new Path(map, m_51, m_52, 200);
		new Path(map, m_42, m_43, 250);
		new Path(map, m_16, m_46, 300);
		new Path(map, m_14, m_29, 250);
		new Path(map, m_30, m_15, 100);
		new Path(map, m_43, m_1, 150);
		new Path(map, m_39, m_40, 250);
		new Path(map, m_40, m_44, 300);
		new Path(map, m_44, m_36, 200);
		new Path(map, m_40, m_37, 100);
		new Path(map, m_35, m_33, 150);
		new Path(map, m_32, m_26, 200);
		new Path(map, m_38, m_41, 250);
		new Path(map, m_41, m_34, 300);
		new Path(map, m_31, m_28, 150);
		new Path(map, m_27, m_28, 100);
		new Path(map, m_23, m_27, 150);
		new Path(map, m_41, m_42, 200);
		new Path(map, m_38, m_39, 250);
		new Path(map, m_40, m_45, 300);
		new Path(map, m_39, m_43, 100);
		new Path(map, m_31, m_34, 100);
		new Path(map, m_32, m_33, 150);
		new Path(map, m_35, m_38, 200);
		new Path(map, m_35, m_37, 250);
		new Path(map, m_24, m_25, 300);
		new Path(map, m_25, m_26, 300);
		new Path(map, m_21, m_22, 100);
		new Path(map, m_22, m_23, 150);
		new Path(map, m_18, m_21, 200);
		new Path(map, m_19, m_24, 250);
		new Path(map, m_10, m_20, 300);
		new Path(map, m_11, m_12, 250);
		new Path(map, m_12, m_14, 100);
		new Path(map, m_14, m_15, 150);
		new Path(map, m_15, m_16, 200);
		new Path(map, m_12, m_13, 250);
		new Path(map, m_13, m_17, 300);
		new Path(map, m_17, m_18, 200);
		new Path(map, m_18, m_19, 100);
		new Path(map, m_16, m_13, 150);
		new Path(map, m_8, m_19, 200);
		new Path(map, m_11, m_18, 250);
		new Path(map, m_7, m_11, 300);
		new Path(map, m_1, m_2, 100);
		new Path(map, m_2, m_3, 100);
		new Path(map, m_4, m_3, 150);
		new Path(map, m_4, m_5, 200);
		new Path(map, m_5, m_6, 250);
		new Path(map, m_7, m_6, 300);
		new Path(map, m_56, m_7, 150);
		new Path(map, m_56, m_8, 100);
		new Path(map, m_9, m_56, 150);
		new Path(map, m_8, m_10, 200);
		new Path(map, m_9, m_10, 250);

		return map;
		// TODO Auto-generated method stub

	}

}
