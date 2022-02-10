
import java.util.*;

import org.w3c.dom.Node;

import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
 class Role
{
    String roleName;
    ArrayList<Role> children;
    Role parent;
   // String userName;
    ArrayList<String> userName;
    Role(String name)
    {
        this.roleName=name;
        this.children=new ArrayList<Role>();
        this.userName=new ArrayList<String>();
    }
}
public class ZohoExam
{
    static Role root;
    static int dist=0;
    //LEVEL-1
    public static void createRoot()
    {
        Scanner scan=new Scanner(System.in);
        //System.out.println("Enter user Name");
        //String uname=scan.next();
        System.out.println("Enter root role Name : ");
        String roleName=scan.next();
        Role newRole=new Role(roleName);
        //newRole.userName.add(uname);
        root=newRole;
        root.parent=null;
        return;
    }
    //LEVEL-2
    public static void addSubRole()
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter subRole name :");
        String subRoleName=scan.next();
        System.out.println("Enter reporting to role name");
        String reportingRoleName=scan.next();
        //System.out.println("Enter the user Name");
        //String uname=scan.next();
        helperSearch(root,reportingRoleName,subRoleName);
    }
    public static void helperSearch(Role root,String reportingRoleName,String subRoleName)
    {
    	Scanner scan=new Scanner(System.in);
        if(root==null)
        {
            return;
        }
        if(root.roleName.equals(reportingRoleName))
        {
        	Role subRoleNode=new Role(subRoleName);
        	//subRoleNode.userName.add(uname);
        	subRoleNode.parent=root;
        	if(root.children==null)
        	{
        		root.children=new ArrayList<Role>();
        	}
            root.children.add(subRoleNode);
            return;
        }
         try
         {
        	for(Role i:root.children)
        	{	
        		if(i.roleName.equals(reportingRoleName))
        		{
        			Role subRoleNode=new Role(subRoleName);
        			i.children.add(subRoleNode);
        			//subRoleNode.userName.add(uname);
        			return;
        		}
        	}
         }
         catch(Exception e)
         {
        	 
         }
        if(root.children!=null)
        {
        	for(Role i:root.children)
            {
                helperSearch(i,reportingRoleName,subRoleName);
            }
        }
    }
    //LEVEL-3
    public static void displayRoles(Role root)
    {
        Queue<Role> mq=new ArrayDeque<>();
        Queue<Role> cq=new ArrayDeque<>();
        mq.add(root);
        while(mq.size()>0)
        {
            Role removed=mq.remove();
            System.out.print(removed.roleName+"  ");
            if(removed.children!=null)
            {
            	for(Role i:removed.children)
                {
                    cq.add(i);
                }
                if(mq.size()==0)
                {
                    mq=cq;
                    cq=new ArrayDeque<>();
                    System.out.println();
                }
            }
            
        }
    }
    //LEVEL-4
    
    public static Role Search(Role root,String name)
    {
    	if(root.roleName.equals(name))
    	{
    		return root;
    	}
    	Queue<Role> mq=new ArrayDeque<>();
    	Queue<Role> cq=new ArrayDeque<>();

    	mq.add(root);
    	while(mq.size()>0)
    	{
    		Role removed=mq.remove();
    		if(removed.roleName.equals(name))
    		{
    			return removed;
    		}
            if(removed.children!=null)
            {
            	for(Role i:removed.children)
                {
                    cq.add(i);
                }
                if(mq.size()==0)
                {
                    mq=cq;
                    cq=new ArrayDeque<>();
                    System.out.println();
                }
            }
    	}
    	return null;
    }
    public static Role SearchBasedOnRole(Role root,String name)
    {
    	if(root.userName.contains(name))
    	{
    		return root;
    	}
    	Queue<Role> mq=new ArrayDeque<>();
    	Queue<Role> cq=new ArrayDeque<>();

    	mq.add(root);
    	while(mq.size()>0)
    	{
    		Role removed=mq.remove();
    		if(removed.userName.contains(name))
    		{
    			return removed;
    		}
            if(removed.children!=null)
            {
            	for(Role i:removed.children)
                {
                    cq.add(i);
                }
                if(mq.size()==0)
                {
                    mq=cq;
                    cq=new ArrayDeque<>();
                    System.out.println();
                }
            }
    	}
    	return null;
    }
    //LEVEL-8
    public static void numberOfUsersInTheTop(String uname)
    {
    	Role r=SearchBasedOnRole(root, uname);
    	if(r==null)
    	{
    		System.out.println("Enter user not present in hierarchy");
    		return;
    	}
    	
    }
    public static void deleteRole(String deleteRole,String ControlTransfer)
    {
    	Role delNode=Search(root,deleteRole);
    	Role ctNode=Search(root,ControlTransfer);
    	Role delParent=delNode.parent;
//    	Role ctParent=ctNode.parent;
    	ArrayList<String> users=delNode.userName;
    	delParent.children.remove(delNode);
    	try
    	{
    		for(Role i:delNode.children)
    		{
    			ctNode.children.add(i);
    			for(String j:users)
    			{
    				ctNode.userName.add(j);
    			}
    		}
    	}
    	catch(Exception e)
    	{
    		System.out.println();
    	}
    }
    public static void addUsers(String uname,String roleName)
    {
    	Role r=Search(root, roleName);
    	if(r.userName==null)
    	{
    		r.userName=new ArrayList<>();
    	}
    	r.userName.add(uname);
    	System.out.println("User successfully added to the "+roleName+"   Role");
    	return;
    }
    //LEVEL -5
    public static void displayUsers(Role root,String findUsers)
    {
    	if(root==null)
    	{
    		System.out.println("No user Found");
    		return;
    	}
    	if(root.roleName.equals(findUsers))
    	{
    		for(String i:root.userName)
    		{
    			System.out.println(i);
    			return;
    		}
    	}
    	Queue<Role> mq=new ArrayDeque<>();
        Queue<Role> cq=new ArrayDeque<>();
        mq.add(root);
        while(mq.size()>0)
        {
        	Role removed=mq.remove();
            if(removed.roleName.equals(findUsers))
            {
            	for(String i:root.userName)
            	{
            		System.out.println(i);
            	}
            }
            if(removed.children!=null)
            {
            	for(Role i:removed.children)
                {
                    cq.add(i);
                }
                if(mq.size()==0)
                {
                    mq=cq;
                    cq=new ArrayDeque<>();
                    System.out.println();
                }
            }
        }
    }
    //LEVEL -5
    public static void displayAllUsers(Role root)
    {
        Queue<Role> mq=new ArrayDeque<>();
        Queue<Role> cq=new ArrayDeque<>();
        mq.add(root);
        while(mq.size()>0)
        {
            Role removed=mq.remove();
            System.out.print(removed.roleName+"   :   ");
            if(removed.userName!=null)
            {
            	for(String i:removed.userName)
            	{
            		System.out.print(i+" , ");
            	}
            }
            System.out.println();
            if(removed.children!=null)
            {
            	for(Role i:removed.children)
                {
                    cq.add(i);
                }
                if(mq.size()==0)
                {
                    mq=cq;
                    cq=new ArrayDeque<>();
                    System.out.println();
                }
            }
            
        }
    }
    //LEVEL-6
    public static void displayUsersAndSubUsers(Role root)
    {
        Queue<Role> mq=new ArrayDeque<>();
        Queue<Role> cq=new ArrayDeque<>();
        mq.add(root);
        while(mq.size()>0)
        {
            Role removed=mq.remove();
            System.out.print(removed.roleName+"   :   ");
            if(removed.children!=null)
            {
            	for(Role i:removed.children)
            	{
            		System.out.print(i.roleName+" , ");
            	}
            }
            System.out.println();
            if(removed.children!=null)
            {
            	for(Role i:removed.children)
                {
                    cq.add(i);
                }
                if(mq.size()==0)
                {
                    mq=cq;
                    cq=new ArrayDeque<>();
                    System.out.println();
                }
            }
            
        }
    }
//  LEVEL-7
    public static void deleteUser(String userName)
    {
    	Role r=SearchBasedOnRole(root, userName);
    	if(r==null)
    	{
    		System.out.println("cannot delete user");
    		return;
    	}
    	ArrayList<String> s=r.userName;
    	if(s.contains(userName))
    	{
    		s.remove(userName);
    	}
    	System.out.println("Successfully Removed the User from "+r.roleName);
    	return;
    }
    //LEVEL-8
    public static void NumberOfUsersFromTheTop(Role root,String find,int distance)
    {
    	if(root.userName.contains(find))
    	{
    		dist=distance;
    	}
    	for(Role i:root.children)
    	{
    		if(i.userName.contains(find))
    		{
    			dist=distance+1;
    			return;
    		}
    	}
    	for(Role i:root.children)
    	{
    		NumberOfUsersFromTheTop(i, find, distance+1);
    	}
    }
    static int totalusers=0;
    public static void usersFromTop(int z)
    {
    	Queue<Role> mq=new ArrayDeque<>();
        Queue<Role> cq=new ArrayDeque<>();
        mq.add(root);
        while(mq.size()>0 && z>0)
        {
            Role removed=mq.remove();
            //System.out.print(removed.roleName+"   :   ");
            if(removed.userName!=null)
            {
            	totalusers+=removed.userName.size();
            }
            z--;
            //System.out.println();
            if(removed.children!=null)
            {
            	for(Role i:removed.children)
                {
                    cq.add(i);
                }
                if(mq.size()==0)
                {
                    mq=cq;
                    cq=new ArrayDeque<>();
                    System.out.println();
                }
            }
            
        }
    }
    //LEVEL-9
    public static int height(Role root)
    {
    	int ht=-1;
    	for(Role i:root.children)
    	{
    		int ch=height(i);
    		ht=Math.max(ch, ht);
    	}
    	ht++;
    	return ht;
    }
    public static ArrayList<String> path(Role root,String uname)
    {
    	if(root.userName.contains(uname))
    	{
    		ArrayList<String> l=new ArrayList<>();
    		l.add(uname);
    		return l;
    	}
    	for(Role i:root.children)
    	{
    		ArrayList<String> ptc=path(i,uname);
    		if(ptc.size()>0)
    		{
    			if(i.userName.size()>0)
    			{
    				ptc.add(root.userName.get(0));
    				return ptc;
    			}
    			return ptc;
    		}
    	}
    	return new ArrayList<String>();
    }
    
    //LEVEL-10
    public static void nearestBoss(String uname1,String uname2)
    {
    	ArrayList<String> path1=path(root,uname1);
    	ArrayList<String> path2=path(root,uname2);
    	int i=path1.size()-1;
    	int j=path2.size()-1;
    	//System.out.println(path1);
    	//System.out.println(path2);
    	if(path1.size()==0 || path2.size()==0)
    	{
    		System.out.println("No common boss Found");
    		return;
    	}
    	try
    	{
    	while(i>0 && j>0 && path1.get(i).equals(path2.get(j)))
    	{
    		i--;
    		j--;
    	}
    	i++;
    	j++;
    	}
    	catch(Exception e)
    	{
    		
    	}
    	System.out.println("Nearest boss for  "+uname1+"  and "+uname2+"   is :"+path1.get(i));
    }
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner scan=new Scanner(System.in);
		createRoot();
		int option,n;
//		System.out.println("Enter Number of Operations");
		n=1000;
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter Your Option");
			option=scan.nextInt();
			if(option==1)
			{
				addSubRole();
			}
			if(option==2)
			{
				displayRoles(root);
			}
			if(option==3)
			{
				System.out.println("Enter the Role to be deleted");
				String s1=scan.next();
				System.out.println("Enter where control should be Transfered");
				String s2=scan.next();
				deleteRole(s1,s2);
			}
			if(option==4)
			{
				System.out.println("Enter the name of user :");
				String uname=scan.next();
				System.out.println("Enter the Role name in which the user should be added");
				String rname=scan.next();
				try
				{
				addUsers(uname, rname);
				}
				catch(Exception e)
				{
					
				}
			}
			if(option==5)
			{
				displayAllUsers(root);
			}
			if(option==6)
			{
				displayUsersAndSubUsers(root);
			}
			if(option==7)
			{
				System.out.println("Enter the name of the User to be Deleted :");
				String dUser=scan.next();
				//System.out.println("Enter the name of the User to be Deleted :");
				deleteUser(dUser);
			}
			if(option==8)
			{
				System.out.println("Enter the userName for which you want to Find Number of Users From the top");
				String s=scan.next();
				dist=0;
				totalusers=0;
				NumberOfUsersFromTheTop(root, s, 0);
				usersFromTop(dist);
				System.out.println(totalusers);
				
			}
			if(option==9)
			{
				System.out.println("Height of hierarchy is :"+height(root));
			}
			if(option==10)
			{
				System.out.println("Enter user name 1");
				String uname1=scan.next();
				System.out.println("Enter user name 2");
				String uname2=scan.next();
				nearestBoss(uname1,uname2);
			}
		}
	}
}


