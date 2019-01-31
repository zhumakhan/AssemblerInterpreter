import java.util.*;
import java.io.*;

public class Assembler{
	public static Map<String, Map<String, String> > InstructionSet = new HashMap<String, Map<String, String>>();
	private static void fillSet(){
		InstructionSet.put("NOP", Map.of("None", "00"));
		InstructionSet.put("MOV", Map.of(
			"A,B", "01",
			"B,A", "02",
			"A,#", "03",
			"B,#", "04",
			"A,Address", "05",
			"B,Address", "06",
			"Address,A", "07",
			"Address,B", "08"
		));
	
		InstructionSet.put("ADD", Map.of(
			"A,B", "09"
		));
		InstructionSet.put("SUB", Map.of(
			"A,B", "0A"
		));
		InstructionSet.put("MUL", Map.of(
			"A,B", "0B"
		));
		InstructionSet.put("DIV", Map.of(
			"A,B", "0C"
		));
		InstructionSet.put("INC", Map.of(
			"A", "0D",
			"B", "0E"
		));

		InstructionSet.put("DEC", Map.of(
			"A", "0F",
			"B", "10"
		));
		InstructionSet.put("AND", Map.of(
			"A,B", "11"
		));
		InstructionSet.put("OR", Map.of(
			"A,B", "12"
		));
		InstructionSet.put("XOR", Map.of(
			"A,B", "13"
		));
		InstructionSet.put("CMP", Map.of(
			"A,B", "14"
		));
		InstructionSet.put("CLR",Map.of(
			"A", "15",
			"B", "16"
		));
		InstructionSet.put("JMP", Map.of(
			"Address", "17"
		));
		InstructionSet.put("JZ", Map.of(
			"Address", "18"
		));
		InstructionSet.put("JNZ", Map.of(
			"Address", "19"
		));
		InstructionSet.put("CPL", Map.of(
			"A", "20",
			"B", "21"
		));
		InstructionSet.put("XCHG", Map.of(
			"A,B", "22"
		));
		InstructionSet.put("HLT", Map.of(
			"None", "23"
		));
			
	}	

	
	public static void main(String args[])throws Exception{
	
		String fileName = args[0];
		BufferedReader br = new BufferedReader(new FileReader(fileName)); 
  		String str, ins, oper, value, addr, temp, res = new String(); 
  		int line = 0;
  		Map<String, String> map = new HashMap<String, String>();
  		
		fillSet();
		
  		while ((str = br.readLine()) != null){ 
    		line++;
    		ins = str.split(" ")[0];
    		if(ins.equals("HLT")){
    			System.out.println("OK");
    			res+="23";
    			break;
    		}
    		String s;
    		oper = str.split(" ")[1];
    		
    		//System.out.println(ins);
    		
    		if((map = InstructionSet.get(ins)) == null){
    			System.out.println(ins+" Syntax Error occured in line: "+line);
    			return;	
    		}else{
    			
    			if(oper.contains("#")){
    				temp = oper.split("#")[0]+"#";
    				value = oper.split("#")[1];
    				
    				//System.out.println(temp);	
    				
    				if( (s = map.get(temp)) == null){System.out.println("Error with operand: "+temp); return;}
    				else{ res += s+value; }
    			}
    			else if(oper.contains(",")){
    				if(oper.split(",")[0].length() == 4){
    					temp = "Address,"+oper.split(",")[1];
    					if( (s = map.get(temp)) == null){System.out.println("Error with operand: "+temp); return;}
    					else{ res += s+oper.split(",")[0]; }		
    				}else if(oper.split(",")[1].length() == 4){
    					temp = oper.split(",")[1]+"Adress";
    					if( (s = map.get(temp)) == null){System.out.println("Error with operand: "+temp); return;}
    					else{ res += s+oper.split(",")[0]; }
    				}else{
    					if( (s = map.get(oper)) == null){System.out.println("Error with operand: "+oper); return;}
    					else{ res += s; }
    				}
    			}else if(oper.length() == 4){
    				if( (s = map.get("Address")) == null){System.out.println("Error with operand: "+oper); return;}
    				else{ res += s; }	
    			}else{
    				if( (s = map.get(oper)) == null){System.out.println("Error with operand: "+oper); return;}
    				else{ res += s; }
    			}	
    		}	 	
  		}
  		System.out.println(res);
	}

}