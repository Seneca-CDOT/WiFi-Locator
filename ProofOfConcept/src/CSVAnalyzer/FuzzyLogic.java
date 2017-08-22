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

			for (int i = 0; i < SurveyNode.size(); i++) {
				for (int j = 0; j < LocNode.get(k).MacAddrs.size(); j++) {
					
					System.out.println("Survey Mac Address = "+ SurveyNode.get(i).MacAddrs.get(0));
					System.out.println("Location Mac Address = "+ LocNode.get(k).MacAddrs.get(j));

					if (SurveyNode.get(i).MacAddrs.contains(LocNode.get(k).MacAddrs.get(j))) {
						
						//We want the strength of the survey node in list i that contains the mac address of Location node j
						//We want the stength of the survey node in list i that conatins the same mac address of location node j
						
						
						
						SurveyNodeStrength = Integer.parseInt(SurveyNode.get(i).NodeSignal.get(SurveyNode.get(j).MacAddrs.indexOf(LocNode.get(k).MacAddrs.get(j))));
						LocNodeStrength = Integer.parseInt(LocNode.get(k).Signal.get(j));

						System.out.print("Survey node strength " + SurveyNodeStrength +" + "+LocNodeStrength+" = "+Math.abs(SurveyNodeStrength - LocNodeStrength));
						
						CurScore += Math.abs(SurveyNodeStrength - LocNodeStrength);
					}
					
					
					
					
				}
				System.out.println("Current score "+CurScore);
				if (CurScore < BestScore) {
					BestNode = SurveyNode.get(i).NodeNum;
					BestScore = CurScore;
				}

				
			}

			output.add(BestNode);

		}

		return output;

	}

}
