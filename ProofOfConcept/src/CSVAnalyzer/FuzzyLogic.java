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
		
		System.out.println(SurveyNode.get(0).toString());
		System.out.println(LocNode.get(0).toString());
		

		for (int k = 0; k < LocNode.size(); k++) {
			
			int CurScore = 0;
			int BestNode = 0;
			int BestScore = 100000;

			for (int i = 0; i < SurveyNode.size(); i++) {
				
				CurScore = 0;
				
				
				for (int j = 0; j < LocNode.get(k).MacAddrs.size(); j++) {
					
					//System.out.println(LocNode.get(k).Signal.get(j));
					

					if (SurveyNode.get(i).MacAddrs.contains(LocNode.get(k).MacAddrs.get(j)) 
							&& (LocNode.get(k).SSID.get(j).contains("ESP")
							|| (!LocNode.get(k).SSID.get(j).contains("NET") && 
									!LocNode.get(k).SSID.get(j).contains("eduroam") && !LocNode.get(k).SSID.get(j).contains("Guest")))
							
							//Only ESP and eduroam
							
							
							) {
						
						//We want the strength of the survey node in list i that contains the mac address of Location node j
						//We want the stength of the survey node in list i that conatins the same mac address of location node j
						
						
						
						SurveyNodeStrength = Integer.parseInt(SurveyNode.get(i).NodeSignal.get(SurveyNode.get(i).MacAddrs.indexOf(LocNode.get(k).MacAddrs.get(j))));
						LocNodeStrength = Integer.parseInt(LocNode.get(k).Signal.get(j));

						System.out.println(SurveyNode.get(i).SSID.get(SurveyNode.get(i).MacAddrs.indexOf(LocNode.get(k).MacAddrs.get(j))) + " strength " + SurveyNodeStrength +" + "+LocNodeStrength+" = "+Math.abs(SurveyNodeStrength - LocNodeStrength));
						
						CurScore += Math.abs(SurveyNodeStrength - LocNodeStrength);
						
					}
					else if(!SurveyNode.get(i).MacAddrs.contains(LocNode.get(k).MacAddrs.get(j)))
					{
						CurScore += 4;
					}
					
					
					
					
				}
				System.out.println(i + " Current score "+CurScore + "-------------------------------------------------------");
				if (CurScore < BestScore) {
					System.out.println("Selected Node is " + i + " ------------------");
					BestNode = i + 1;
					BestScore = CurScore;
				}

				
			}

			output.add(BestNode);

		}

		return output;

	}

}
