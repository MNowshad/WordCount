# WordCount
Returns the top N word from a dataset at the moment . This is done with scalability in mind. 
This project is work in progress, will be adding more functionalities

#Assumption
This program returns the top N words.  If rank is also needed then we can  do a radix sort on the final top N words.

# Performance analysis
Overall complexity of the program is O(n) time and O(n) space .
My program is divided into four parts:
  1.	Reading the chunks of data   and adding it to dictionary. 
      Running time complexity: O(n)  
      Space complexity :  O(k) 
       where k is the size of dictionary, distinct words of dataset. 
              n is the size of dataset. 
       K<=n ; 
  2.	Adding the values of dictionary into array.
      Running time complexity: O(k)
      Space complexity: O(k)
  3.	QuickSelect algorithm to pick up the Kth greatest value :
      Running time complexity: O(k) . It is optimized to have a random pivot value so it reduces the chance of worst case complexity of O(k^2) .
      Space complexity: O(1).
  4.	Looping through the dictionary to find the top 10 elements and adding it to array. 
  	  Running time complexity: O(k)  
  	  Space complexity: O(1) constant as it will be always top ten records size. 

#Large File issue
The complexity that arrives with large file is that it can exhaust the memory and take a much longer time (worst if the runtime is exponential).
To improve the runtime performance I came up with a O(n) solution which will stay linear if the datasets are increased in size. 
 I read the file in chunks and kept in byte Array and do all the read operation to build the dictionary ( Hashmap)object avoiding the default string operation which takes longer to execute.
In ideal scenario the dictionary wont exhaust memory as there are will be lot of common words in the dataset. 
In rare case where every word is different and the file size is really large, dictionary will exhaust memory and this solution will fail in that scenario. 
The other issue is CPU is mostly sitting idle and program is not taking the full advantage of multiple cores. 
