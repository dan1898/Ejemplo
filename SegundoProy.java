package SegundoProyecto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

import proyectos.Estudiante;

public class SegundoProy<cops> {
	Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		SegundoProy pr = new SegundoProy();
		pr.principal();

	}
	public void principal() throws IOException {
		byte op1;
		do {
		System.out.println("menu de opciones");
   		System.out.println("1.Verificar si existe el archivo de Datos");
   		System.out.println("2.Crear Archivo");
   		System.out.println("3.asignar nombre a los campos ");
   		System.out.println("4.Agregar ");
   		System.out.println("5.Modificar ");
   		System.out.println("6.si desea salir ");
   		
   		op1 = scan.nextByte();
 		switch(op1){
   		case 1 :
   		
   			if (verificarArchivo()){
				 System.out.print("\nEl archivo ya existe");
			 }else {
				 System.out.print("\nEl archivo no existe");
			 }
   			break;
   		case 2 :
   		 if (crearArchivo()) {
			 System.out.print("\n El archivo ha sido creado satisfactoriamente");
		}
   		 break;
   			   
   		case 3:
   			asignacion();
   			break;
   		case 4 :
   			  if(agregarRegistro()) {
   			   System.out.println("se agregaron los datos al registro");
   			   }
   			   break;
   		case 5:
   		 // if(buscarRegistro()) {
   			//	 System.out.println("su busqueda se cargo con exito");
   			//}
   		
   		default:
   			break;
   			   
   			}
		}while(op1!=9);
	}
	
	
	public void asignacion() throws IOException {
		RandomAccessFile rac = new RandomAccessFile("c:/archivo/asignar.txt","rw");
		System.out.println("ingrese los nombres que le quiere dar a los campos con los que contara en el registro/n");	
		String n,s,f,os,c,b;
		System.out.println("ingrese el nombre para el campo ");
		System.out.println("seccion para codigo unico o identificador del registro");
		scan.nextLine();
		n=scan.nextLine();
		System.out.println("ingrese el nombre del siguiente campo ");
		System.out.println("seccion para agregar oraciones o lineas de caracteres");
		s=scan.nextLine();
		System.out.println("ingrese el nombre del siguente campo ");
		System.out.println("seccion de datos asignada para datos con punto decimal  ");
		f=scan.nextLine();
		System.out.println("ingrese el nombre para el campo ");
		System.out.println("secion de datos para agregar lineas de caracteres mas extensa");
		os=scan.nextLine();
		System.out.println("ingrese el nombre para el campo");
		System.out.println("seccion de datos para agregar una sola letra");
		c=scan.nextLine();
		System.out.println("ingrese el nombre para el campo ");
		System.out.println("seccion de datos para agregar cantidades peque#as");
		b=scan.nextLine();
		rac.writeUTF(n);
		rac.writeBytes(" ");
		rac.writeUTF(s);
		rac.writeBytes(" ");
		rac.writeUTF(f);
		rac.writeBytes(" ");
		rac.writeUTF(os);
		rac.writeBytes(" ");
		rac.writeUTF(c);
		rac.writeBytes(" ");
		rac.writeUTF(b);

		rac.close();
		
		
		
		
	}
	
	
	
	
	
	public boolean crearArchivo() { 
		try {
			final String ruta = "c:/archivo/DATOS.txt"; 
			final String ruta2 = "c:/archivo/INDEX.txt"; 
			final String ruta3= "c:/archivo/asignar.txt";
			File archivo= new File(ruta2);
			File archivo2= new File(ruta);
			File archivo3= new File(ruta3);
			archivo2.createNewFile();
			archivo3.createNewFile();
			return archivo.createNewFile();
			
		}catch(IOException e ){
			System.out.print("ocurrio un error al crear el archivo\n"+e.getMessage());
			return false;
		}
	}
	public boolean verificarArchivo() {
		final String ruta = "c:/archivo/DATOS.txt"; 
		File archivo= new File(ruta);
		return archivo.exists();
		}
	
	public boolean agregarRegistro() throws IOException {
		try {
			File archivo = new File("C:/archivo/asignar.txt");
			FileReader fr = new FileReader(archivo);
			BufferedReader br= new BufferedReader(fr);
			String texto = br.readLine();
			String [] palabras= texto.split(" ");
			
			for(int i=0;i<palabras.length;i++) {
				System.out.println("es "+palabras[i]);
			}
		int cant;
		long direccion;
		
		System.out.println("ingrese el numero de registros a ingresar");
		cant = scan.nextInt();
		cpos es[]= new cpos[cant];
		
		char op='1';
		RandomAccessFile raf1 = null;
		RandomAccessFile raf2 = null;
		
			try {
			 raf1= new RandomAccessFile( "C:/archivo/INDEX.txt","rw");
			 raf2= new RandomAccessFile( "C:/archivo/DATOS.txt","rw");
				}catch(IOException e ) {
					System.out.print("a ocurrido un error al cargar archivos "+ e.getMessage());
				}
			
			 op='1';
		for(int i=0; i<es.length;i++) {
			
			if(raf1!= null && raf2!=null) {
				do {	
					
					for(int i1=0; i1<es.length;i1++) {
						System.out.println("ingrese los datos que se le solicitan ");
						System.out.println("ingrese primer campo "+palabras[0]);
						int n =scan.nextInt();
						System.out.println("ingrese segundo campo"+palabras[1]);
						scan.nextLine();
						String s=scan.nextLine();
						System.out.println("ingrese tercer campo  "+palabras[2]);
						float  f  =scan.nextFloat();
						System.out.println("ingrese cuarto campo "+palabras[3]);
						scan.nextLine();
						String os=scan.nextLine();
						System.out.println("ingrese el quinto campo");
						char  c =scan.next().charAt(0);
						System.out.println("ingrese el sexto campo");
						byte b = scan.nextByte();
						es[i1]=new cpos(n,s,f,os,c,b);
						}
					try {
						raf1.seek(raf1.length());
						raf2.seek(raf2.length());
						direccion= raf2.getFilePointer();
						
						raf2.writeInt(es[i].getN());
						raf2.writeUTF(es[i].getS());
						raf2.writeFloat(es[i].getF());
						raf2.writeUTF(es[i].getOs());
						raf2.writeChar(es[i].getC());
						raf2.writeByte(es[i].getB());
					    
						
						raf1.writeInt(es[i].getN());
						raf1.writeLong(direccion);
						System.out.println("si desea salir ingrese el no.1");
						op= scan.next().charAt(0);
						}catch(Exception e) {
							System.out.println("valor inavalido");
						}
			
			
		
		}while(op !='1');
			}
		}
		return true;
		}catch(Exception e) {
			System.out.println("no se ha podido agregar al registro ");
		return false;
		}
	}
	
	public void modificarRegistro() {
		cpos es[]= new cpos[1];
		int llave;
		char op='1';
		long tmpRegistro=0,desplazador=0;
		RandomAccessFile raf1= null;
		RandomAccessFile raf2= null;
		try {
			raf1= new RandomAccessFile("C:/archivo/DATOS.txt","rw");
			raf2= new RandomAccessFile("C:/archivo/DATOS.txt","rw");
		}catch(Exception e) {
			System.out.println("no se pudo abrir el archivo de datos");
		}
		if(raf1 != null) {
			try {
			raf1.readInt();
			raf1.readUTF();
			raf1.readFloat();
			raf1.readUTF();
			raf1.readChar();
			raf1.readUnsignedByte();
			tmpRegistro= raf1.getFilePointer();
			}catch(Exception e) {
				System.out.println("no se pudo abrir el archivo de datos ");
			}
			do {
				try {
					System.out.println("Ingrese la clave");
					llave= scan.nextInt();
					desplazador= (llave-1)*tmpRegistro;
					raf1.seek(desplazador);
					raf1.readInt();
					
					System.out.println("ingrese campo 1");
					int nu = scan.nextInt();
					System.out.println("ingrese campo 2");
					scan.nextLine();
					String n= scan.nextLine();
					System.out.println("ingrese campo 3");
					float e= scan.nextFloat();
					System.out.println("ingrese el campo 4");
					scan.nextLine();
					String os= scan.nextLine();
					System.out.println("ingrese campo 5");
					char s= scan.next().charAt(0);
					System.out.println("ingrese campo 6");
					byte b= scan.nextByte();
					raf1.writeInt(nu);
					raf1.writeUTF(n);
					raf1.writeFloat(e);
					raf1.writeUTF(os);
					raf1.writeChar(s);
					raf1.writeByte(b);
					
					
					System.out.println("\n Desea modificar otro registro");
					System.out.println("[no]=1");
					System.out.println("[si]=cualquier otro numero");
					System.out.println("ingrese la opcion ");
					op= scan.next().charAt(0);
				}catch(Exception e) {
					System.out.println("el valor ingresado no se encuentra en el registro");
				}
			}while(op !='1');
	}
		
	}

}

