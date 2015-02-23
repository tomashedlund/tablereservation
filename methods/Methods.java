package methods;

import gui.GBench;
import gui.GChair;
import gui.GTable;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JPanel;

import types.Bench;
import types.Chair;
import types.Table;

public class Methods {
	public static char[][] textFileToCharArray(String filename) {
		char[][] output = new char[100][100];
		try (BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			int line = 0;
			String readLine;
			while ((readLine = br.readLine()) != null) {
				output[line] = readLine.toCharArray();
				line++;
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return output;
	}
	public static void dfs(Point where, Set<Point> visited, Map<Point,Object> map){
		visited.add(where);
		for(int i = -1; i <= 1; i+=1)
			for(int j = -1; j <= 1; j+=1){
				if (!visited.contains(new Point(where.x+i,where.y+j)) && map.get(new Point(where.x+i,where.y+j)) instanceof GTable)
					dfs(new Point(where.x+i,where.y+j), visited,map);
			}
		
	}
	public static void buildTables(Map<Point,Object> map, Map<Table,Set<Point>> allTables) {
		Set<Point> allVisited = new HashSet<Point>();
		for (Entry<Point, Object> e : map.entrySet()){
			Set<Point> table = new HashSet<Point>();
			if(e.getValue() instanceof GTable && !allVisited.contains(e.getKey())){
				dfs(((GTable)e.getValue()).possition(),table,map);
				allVisited.addAll(table);
				int numberOfPlates = 0;
				for(Point p : table)
					if (((GTable)map.get(p)).hasPlate())
						numberOfPlates++;
				allTables.put(new Table(numberOfPlates), table);
			}
		}
	}
	
	public static Map<Point,Object> charArrayToAnTableMap(char[][] input, JPanel display, int blockSize) {
		Map<Point,Object> output = new HashMap<Point,Object>();
		GTable table;
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input[i].length; j++)
				switch (input[i][j]) {
					case 'C':
						output.put(new Point(j,i), new Chair());
						display.add(new GChair(j*blockSize,i*blockSize,blockSize));
						break;
					case 'B':
						output.put(new Point(j,i), new Bench());
						display.add(new GBench(j*blockSize,i*blockSize,blockSize));
						break;
					case 'S':
						table = new GTable(j*blockSize,i*blockSize,blockSize,true,new Point(j,i));
						output.put(new Point(j,i), table);
						display.add(table);
						break;
					case 'T':
						table = new GTable(j*blockSize,i*blockSize,blockSize,false,new Point(j,i));
						output.put(new Point(j,i), table);
						display.add(table);
						break;
				}
		}
		return output;
	}
	
}
