package CSVAnalyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ethchil
 *
 */

public class FuzzyLogic {

	public static List<Integer> CalcFuzzyLogic(List<Node> LocNode, List<Node> CurNode)
	{
		int LocNodeStrength = 0;
		int CurNodeStrength = 0;
		List<Integer> output = new ArrayList<Integer>();

		for(int k = 0; k < CurNode.size(); k++)
		{
			int CurScore = 0;
			int BestNode = 0;
			int BestScore = 999999;
			
			for(int j = 0; j < LocNode.size(); j++)
			{
				for(int i = 0; i < CurNode.get(k).MacAddrs.size(); i++)
				{
					if(LocNode.get(j).MacAddrs.contains(CurNode.get(k).MacAddrs.get(i)))
					{
						LocNodeStrength = Integer.parseInt(LocNode.get(j).NodeSignal.get(LocNode.get(j).MacAddrs.indexOf(CurNode.get(k).MacAddrs.get(i))));
						CurNodeStrength = Integer.parseInt(CurNode.get(k).NodeSignal.get(i));

						CurScore += Math.abs(LocNodeStrength - CurNodeStrength);
					}
				}
				
				if(CurScore < BestScore)
				{
					BestNode = j;
					BestScore = CurScore;
				}
			}
			
			output.add(BestNode);
			
		}
		
		return output;

	}

}
