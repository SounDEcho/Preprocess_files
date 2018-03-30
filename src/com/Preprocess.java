package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
public class Preprocess{

	public static void main(String[] args)
	{

		String indir = args[0];
		String outdir = args[1];

		File dir = new File(indir);


		String[] files = dir.list((temp, name) -> name.endsWith(".c"));

		File[] file_with_path = dir.listFiles((temp, name) -> name.endsWith(".c"));

		String[] files_dup = new String[files.length];

		for (int i = 0;i<files.length;i++)
		{
			files_dup[i] = files[i].replace(".c",".prepro");
		}
		String[] command = {"c:\\LegacyApp\\CTC_AURIX\\6.2r1p4\\ctc\\bin\\ctc.exe","-Ec","-I",null/*indir*/,null/*file_with_path*/,"-o",null/*out_file*/};
		try {
			Runtime.getRuntime().exec("mkdir "+outdir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dummy;
		for (int i = 0;i<files.length;i++)
		{
			command[3]=indir;/*include directory*/
			dummy=""+file_with_path[i];
			command[4]=dummy;/*file name with path*/
			command[6] = indir+"/temp/"+files_dup[i];


			try {
				System.out.println("Executing : "+command[4]);
				Process proc = Runtime.getRuntime().exec(command);

				BufferedReader stdError = new BufferedReader(new
				InputStreamReader(proc.getErrorStream()));
				String s = null;
				System.out.println("Error log for "+dummy+":");
				while ((s = stdError.readLine()) != null) {
					System.out.println(s);
				}
				System.out.println("Execution done\n\n");

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
