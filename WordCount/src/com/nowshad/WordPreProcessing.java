package com.nowshad;

import com.nowshad.CustomString;

public class WordPreProcessing {
	/**
	    * This method does the function of toLowerCase and replace 
	    * It replaces all the special character with ',' which is later used for word separation delimiter.
	    * this speeds up the computation of default String object library. 
	    * @param line
	    * @return mutable CustomString
	    */
	   public  CustomString toLowerAndReplace(CustomString line) {
	       if (line == null)
	           return null;
	       
	       for (int i = line.start ; i < line.end; i++) {
	       	
	       	switch(line.data[i]) {
	       	case 'A' :
	       		line.data[i]= 'a';
	       		break;
	       	case 'B' :
	       		line.data[i]='b';
	       		break;
	       	case 'C' : 
	       		line.data[i]='c';
	       		break;
	       	case 'D' :
	       		line.data[i]= 'd';
	       		break;
	       	case 'E' :
	       		line.data[i]='e';
	       		break;
	       	case 'F' : 
	       		line.data[i]='f';
	       		break;
	       	case 'G' :
	       		line.data[i]= 'g';
	       		break;
	       	case 'H' :
	       		line.data[i]='h';
	       		break;
	       	case 'I' : 
	       		line.data[i]='i';
	       		break;
	       	case 'J' :
	       		line.data[i]= 'j';
	       		break;
	       	case 'K' :
	       		line.data[i]='k';
	       		break;
	       	case 'L' : 
	       		line.data[i]='l';
	       		break;
	       	case 'M' :
	       		line.data[i]= 'm';
	       		break;
	       	case 'N' :
	       		line.data[i]='n';
	       		break;
	       	case 'O' : 
	       		line.data[i]='o';
	       		break;
	       	case 'P' :
	       		line.data[i]= 'p';
	       		break;
	       	case 'Q' :
	       		line.data[i]='q';
	       		break;
	       	case 'R' : 
	       		line.data[i]='r';
	       		break;
	       	case 'S' :
	       		line.data[i]= 's';
	       		break;
	       	case 'T' :
	       		line.data[i]='t';
	       		break;
	       	case 'U' : 
	       		line.data[i]='u';
	       		break;
	       	case 'V' :
	       		line.data[i]= 'v';
	       		break;
	       	case 'W' :
	       		line.data[i]='w';
	       		break;
	       	case 'X' : 
	       		line.data[i]='x';
	       		break;
	       	case 'Y' :
	       		line.data[i]= 'y';
	       		break;
	       	case 'Z' :
	       		line.data[i]='z';
	       		break;
	       		
	       	case '<' :
	       	case '>' :
	       	case '&' :
	       	case '\"' :
	       	case '\t' :
	       	case '!' :
	       	case '#' :
	       	case '$' :
	       	case '(' :
	       	case ')' :
	       	case '*' :
	       	case '+' :
	       	case '=' :
	       	case '-' :
	       	case '.' :
	       	case '/' :
	       	case ':' :
	       	case ';' :
	       	case '?' :
	       	case '@' :
	       	case '\\' :
	       	case '[' :
	       	case ']' :
	       	case '^' :
	       	case '_' :
	       	case '{' :
	       	case '|' :
	       	case '}' :
	       	case '~' :
	       	case ' ' :
	       		line.data[i]=',';
	       		break;
	       	}
	       	

	       }
	       return line;
	   }

}
