package CSVAnalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ethchil
 *
 */

public class FuzzyLogic {

	public static List<Integer> CalcFuzzyLogic(List<Node> SurveyNode, List<ESPLocation> LocNode) {
		int SurveyNodeStrength = 0;
		int LocNodeStrength = 0;
		List<Integer> output = new ArrayList<Integer>();

		for (int k = 0; k < LocNode.size(); k++) {
			int CurScore = 0;
			int BestNode = 0;
			int BestScore = 999999;

			for (int j = 0; j < LocNode.get(k).MacAddrs.size(); j++) {
				for (int i = 0; i < SurveyNode.size(); i++) {

					if (SurveyNode.get(i).MacAddrs.contains(LocNode.get(k).MacAddrs.get(j))) {
						
						//We want the strength of the survey node in list i that contains the mac address of Location node j
						//We want the stength of the survey node in list i that conatins the same mac address of location node j
						
						SurveyNodeStrength = Integer.parseInt(SurveyNode.get(i).NodeSignal.get(LocNode.get(j).MacAddrs.indexOf(LocNode.get(k).MacAddrs.get(j))));
						LocNodeStrength = Integer.parseInt(LocNode.get(k).Signal.get(j));

						CurScore += Math.abs(SurveyNodeStrength - LocNodeStrength);
					}
				}

				if (CurScore < BestScore) {
					BestNode = SurveyNode.get(j).NodeNum;
					BestScore = CurScore;
				}
			}

			output.add(BestNode);

		}

		return output;

	}

}
