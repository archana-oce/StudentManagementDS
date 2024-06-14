import java.util.Scanner;

class Student 
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        boolean loop=true;
        StudentData s=new StudentData();
        while(loop)
        {
            System.out.println("---------------------------------------");
            System.out.println("                  MENU                 ");
            System.out.println("---------------------------------------");
            System.out.println("1) Insert Student Data ");
            System.out.println("2) Update Student Data ");
            System.out.println("3) Delete Student Data ");
            System.out.println("4) Search Student Data ");
            System.out.println("5) Display Student Data ");
            System.out.println("6) Exit ");

            System.out.println(" Enter your choice : ");
            int choice=sc.nextInt();

            switch(choice)
            {
                case 1:
                {
                    boolean loop2 = true;
                    int roll_no=0;
                    
                    while (loop2) 
                    {
                        System.out.println(" Enter Student's Roll no : ");
                        roll_no = sc.nextInt();
                    
                        if (s.isRollNoExists(roll_no)) {
                            System.out.println("Roll number already exists. Duplicate entry not allowed.");
                        } else {
                            break;
                        }
                    }
                    
                    System.out.println(" Enter Student's name : ");
                    String name = sc.next();
                    System.out.println(" Enter Student's department : ");
                    String department = sc.next();
                    System.out.println(" Enter Student's java marks : ");
                    double java_marks = sc.nextDouble();
                    System.out.println(" Enter Student's DBMS marks : ");
                    double dbms_marks = sc.nextDouble();
                    System.out.println(" Enter Student's DS marks : ");
                    double ds_marks = sc.nextDouble();
                    
                    s.insertDataInOrderedWay(roll_no, name, department, java_marks, dbms_marks, ds_marks);
                    break;
                }

                case 2:
                {
                    System.out.println(" Enter the roll_no of student whose marks you want to update : ");
                    int search_roll_no=sc.nextInt();

                    if (s.isRollNoExists(search_roll_no)) 
                    {
               
                        System.out.println(" Enter which subject's marks you want to update : ");
                        String update_subj=sc.next();

                        System.out.println(" Enter the updated mark of student : ");
                        double updated_mark=sc.nextDouble();

                        s.updateMarks(search_roll_no,updated_mark,update_subj);
                        break;
                    }
                    else
                    {
                        System.out.println("Roll number does not exists.");
                        break;
                    }
                }

                case 3:
                {
                    System.out.println("Enter the id that you want to delete : ");
                    int delete_roll_no=sc.nextInt();

                    s.deleteData(delete_roll_no);
                    break;
                }

                case 4:
                {
                    boolean loop2=true;
                    while(loop2)
                    {
                        System.out.println("---------------------------------------");
                        System.out.println("             SEARCH MENU               ");
                        System.out.println("---------------------------------------");
                        System.out.println("1) Search using Student's name ");
                        System.out.println("2) Search using roll no. ");
                        System.out.println("3) Search Students of particular department ");
                        System.out.println("4) Search students with particular total marks : ");
                        System.out.println("5) Search students using marks in particular subject : ");
                        System.out.println("6) Exit ");

                        System.out.println("\n Enter your choice : ");
                        int choice3=sc.nextInt();
                        switch(choice3)
                        {
                            case 1:
                            {
                                System.out.println(" Enter Students's name : ");
                                String search_name=sc.next();

                                s.searchByName(search_name);
                                break;
                            }
                            case 2:
                            {
                                System.out.println(" Enter Student's roll_no : ");
                                int search_roll_no=sc.nextInt();

                                s.searchByRollNo(search_roll_no);
                                break;
                            }
                            case 3:
                            {
                                System.out.println(" Enter department : ");
                                String search_dept=sc.next();

                                s.studentParticularDept(search_dept);
                                break;
                            }
                            case 4:
                            {
                                System.out.println(" Enter  marks for total : ");
                                double passMarks=sc.nextDouble();

                                s.searchParticularMarksOrLessStudents(passMarks);
                                break;
                            }
                            case 5:
                            {
                                System.out.println(" Enter the subject : ");
                                String subject=sc.next();
                                System.out.println(" Enter marks for "+subject+" : ");
                                double passMarks=sc.nextDouble();

                                s.searchSubjectMarksOrLessStudents(passMarks, subject);
                                break;
                            }
                            case 6:
                            {
                                loop2=false;
                                break;
                            }
                            default:
                            {
                                System.out.println(" Enter a valid choice! ");
                                break;
                            }
                        }
                    }
                    break;
                }

                case 5:
                {   s.display();
                    break;
                }

                case 6:
                {
                    loop=false;
                    break;
                }

                default:
                {
                    System.out.println(" Enter a valid choice !");
                    break;
                }
            }
        }
    } 
}    

class StudentData
{
    class Node 
    {
        int roll_no;
        String name;
        String department;
        double java_marks;
        double dbms_marks;
        double ds_marks;
        
        Node next;
        Node previous;

            Node(int roll_no, String name, String department, double java_marks,double dbms_marks,double ds_marks) 
            {
                this.roll_no = roll_no;
                this.name = name;
                this.department = department;
                this.java_marks = java_marks;
                this.dbms_marks=dbms_marks;
                this.ds_marks=ds_marks;
                this.next = null;
                this.previous = null;
            }
    }

    Node first=null;

    boolean isRollNoExists(int roll_no) 
    {
        if (first == null) 
        {
            return false;
        }

        Node temp = first;
        do 
        {
            if (temp.roll_no == roll_no) 
            {
                return true;
            }
            temp = temp.next;
        } while (temp != first);

        return false;
    }

    
    void insertDataInOrderedWay(int roll_no, String name, String department, double java_marks, double dbms_marks, double ds_marks) 
    {
        Node n = new Node(roll_no, name, department, java_marks, dbms_marks, ds_marks);

        if (first == null) //pointer set if no data present
        {
            first = n;
            n.next = n;
            n.previous = n;
        } 
        else if (roll_no < first.roll_no) //swapping 
        {
            
            n.next = first;
            n.previous = first.previous; 
            first.previous.next = n;
            first.previous = n;
            first = n; 
        } 
        else 
        {
            Node temp = first;
            while (temp.next != first && roll_no >= temp.next.roll_no) 
            {
                temp = temp.next;
            }
            
            n.next = temp.next;
            n.previous = temp;
            temp.next.previous = n;
            temp.next = n;
        }
    }

    void updateMarks(int search_roll_no, double updated_mark, String subject) 
    {
        if (first == null) 
        {
            System.out.println("Data is empty. Couldn't update marks.");
            return;
        }

        Node temp = first;
        int found = 0;

        do 
        {
            if (temp.roll_no == search_roll_no) 
            {
                switch (subject.toLowerCase()) 
                {
                    case "java":
                        temp.java_marks = updated_mark;
                        System.out.println("Marks in Java updated successfully.");
                        break;
                    case "dbms":
                        temp.dbms_marks = updated_mark;
                        System.out.println("Marks in DBMS updated successfully.");
                        break;
                    case "ds":
                        temp.ds_marks = updated_mark;
                        System.out.println("Marks in DS updated successfully.");
                        break;
                    default:
                        System.out.println("No subject named: " + subject);
                        return;
                }
                found = 1;
            }
            temp = temp.next;
        } while (temp != first);

        if (found == 0) 
        {
            System.out.println("Student with given roll number not found.");
        }
    }

    void deleteData(int delete_roll_no)
    {
        if (first == null) 
        {
            System.out.println("Data is empty");
            return;
        }

        Node temp = first;
        do 
        {
            if (temp.roll_no == delete_roll_no) 
            {
                if (temp == first) 
                {
                    first = first.next;
                    first.previous = temp.previous;
                    temp.previous.next = first;
                } 
                else 
                {
                    temp.previous.next = temp.next;
                    temp.next.previous = temp.previous;
                }
                return;
            }
            temp = temp.next;
        } while (temp != first);

        System.out.println("No roll nuumber of such exists");
    }

    void searchByName(String searchName) 
    {
        if (first == null) 
        {
            System.out.println("Data is empty.");
            return;
        }

        Node temp = first;
        int found=0;

        do 
        {
            if (temp.name.equalsIgnoreCase(searchName)) //if it is equal to searchName
            {
                System.out.println("Roll no : " + temp.roll_no);
                System.out.println("Name : " + temp.name);
                System.out.println("Department : " + temp.department);
                System.out.println(" Java Marks : "+temp.java_marks);
                System.out.println(" DBMS marks : "+temp.dbms_marks);
                System.out.println("DS marks : "+temp.ds_marks);

                double totalMarks=temp.java_marks+temp.dbms_marks+temp.ds_marks;

                System.out.println("total marks : "+totalMarks);

                found = 1;
            }
            temp = temp.next;
        } while (temp != first);

        if (found==0) 
        {
            System.out.println("Student with the name '" + searchName + "' not found.");
        }
    }

    void searchByRollNo(int search_roll_no)
    {
        if(first==null)
        {
            System.out.println(" Data is empty ");
            return;
        }

        Node temp=first;
        int found=0;

        do
        {
            if(temp.roll_no == search_roll_no)
            {
                System.out.println("Roll no : " + temp.roll_no);
                System.out.println("Name : " + temp.name);
                System.out.println("Department : " + temp.department);
                System.out.println(" Java Marks : "+temp.java_marks);
                System.out.println(" DBMS marks : "+temp.dbms_marks);
                System.out.println("DS marks : "+temp.ds_marks);

                double totalMarks=temp.java_marks+temp.dbms_marks+temp.ds_marks;

                System.out.println("total marks : "+totalMarks);

                found = 1;
            }
            temp = temp.next;
        }while(temp != first);

        if (found==0) 
        {
            System.out.println("Student with the roll no -  '" + search_roll_no + "' not found.");
        }
    }

    void studentParticularDept(String search_dept)
    {
        if (first == null) 
        {
            System.out.println("Data is empty.");
            return;
        }

        Node temp = first;
        int found=0;

        do 
        {
            if (temp.department.equalsIgnoreCase(search_dept)) 
            {
                System.out.println("Roll no : " + temp.roll_no);
                System.out.println("Name : " + temp.name);
                System.out.println("Department : " + temp.department);
                System.out.println("Java Marks : " + temp.java_marks);
                System.out.println("DBMS Marks : " + temp.dbms_marks);
                System.out.println("DS Marks : " + temp.ds_marks);

                double totalMarks=temp.java_marks+temp.dbms_marks+temp.ds_marks;

                System.out.println("total marks : "+totalMarks);

                found = 1;
            }
            temp = temp.next;
        } while (temp != first);

        if (found==0) 
        {
            System.out.println("Students with the department '" + temp.department + "' not found.");
        }
    }
 
    void searchParticularMarksOrLessStudents(double passMarks) //Students data with total marks which are less than or equal to pass Marks will be displayed
    {
        if (first == null) 
        {
            System.out.println("Data is empty.");
            return;
        }

        Node temp = first;
        int found = 0;

        do 
        {
            
            double totalMarks = temp.java_marks + temp.dbms_marks + temp.ds_marks;

            if (totalMarks <= passMarks) 
            {
                System.out.println("Roll no: " + temp.roll_no);
                System.out.println("Name: " + temp.name);
                System.out.println("Department: " + temp.department);
                System.out.println("Java Marks : " + temp.java_marks);
                System.out.println("DBMS Marks : " + temp.dbms_marks);
                System.out.println("DS Marks : " + temp.ds_marks);

                System.out.println("Total Marks: " + totalMarks);
                
                found = 1;
            }
            temp = temp.next;
        } while (temp != first);

        if (found == 0) 
        {
            System.out.println("No fail students found based on total marks.");
        }
    }

    void searchSubjectMarksOrLessStudents(double passMarks,String subject) 
    {//Students data with (for particular subject) marks which are less than or equal to pass Marks will be displayed
        
        if (first == null) 
        {
            System.out.println("Data is empty.");
            return;
        }

        Node temp = first;
        int found = 0;

        do 
        {
            double subjectMarks = 0.0; 

            switch (subject.toLowerCase()) {
                case "java":
                {
                    subjectMarks = temp.java_marks;
                    break;
                }
                case "dbms":
                {
                    subjectMarks = temp.dbms_marks;
                    break;
                }
                case "ds":
                {
                    subjectMarks = temp.ds_marks;
                    break;
                }
                default:
                {
                    System.out.println("No subject named : " + subject);
                
                    return; 
                }
            }

            
            if (subjectMarks <= passMarks) 
            {
                System.out.println("Roll no: " + temp.roll_no);
                System.out.println("Name: " + temp.name);
                System.out.println("Department: " + temp.department);
                System.out.println("Marks in " + subject + ": " + subjectMarks);

                found = 1;
            }

            temp = temp.next;

        } while (temp != first);

        if (found == 0) 
        {
            System.out.println("No fail students found based on " + subject + " marks.");
        }
    }

    void display()
    {
        if(first==null)
        {
            System.out.println(" Data empty ");
            return;
        }
        else
        {
            Node temp=first;
            System.out.println("\n------------------------------------------");
            System.out.println("                  DISPLAY                 ");
            System.out.println("------------------------------------------");
            do
            {
                System.out.println(" Roll no : "+temp.roll_no);
                System.out.println(" Name : "+temp.name);
                System.out.println(" Department : "+temp.department);
                System.out.println(" Java Marks : "+temp.java_marks);
                System.out.println(" DBMS marks : "+temp.dbms_marks);
                System.out.println(" DS marks : "+temp.ds_marks);

                double totalMarks=temp.java_marks+temp.dbms_marks+temp.ds_marks;

                System.out.println("total marks : "+totalMarks);

                temp = temp.next;
            } while(temp != first);
        }
    }
}