package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.model.BookInfo;
import com.model.BookType;
import com.model.Operater;
import com.model.Reader;
import com.model.Back;
import com.model.user;

//连接数据库
public class Dao {
	static String ClassName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/db_library";
	static String user = "root";
	static String pwd = "root";
	static Connection conn = null;

	public Dao() {
		try {
			//加载驱动
			if (conn == null) {
				Class.forName(ClassName).newInstance();
				conn = DriverManager.getConnection(url,user, pwd);
			}
			else
				return;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static ResultSet executeQuery(String sql) {
		try {
			if(conn==null)
				new Dao();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int executeUpdate(String sql) {
		try {
			if(conn==null)
				new Dao();
			return conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
		}
	}
	//关闭数据库连接
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}
	//管理员登录
	public static Operater check(String name, String password) {
		int i = 0;
		Operater operater=new Operater();
		String sql = "select *  from operator where name='" + name
				+ "' and password='" + password + "'and admin=1";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				String names = rs.getString(1);
				operater.setId(rs.getString("id"));
				operater.setName(rs.getString("name"));
				operater.setGrade(rs.getString("admin"));
				operater.setPassword(rs.getString("password"));
				if (names != null) {
					i = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return operater;
	}
	//图书类别
	public static List selectBookCategory() {
		List list=new ArrayList();
		String sql = "select *  from bookType";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType bookType=new BookType();
				bookType.setId(rs.getString("id"));
				bookType.setTypeName(rs.getString("typeName"));
				bookType.setDays(rs.getString("days"));
				bookType.setFk(rs.getString("fk"));
				list.add(bookType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;

	}
	public static List selectBookCategory(String bookType) {
		List list=new ArrayList();
		String sql = "select days  from bookType where typeName='"+bookType+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookType type=new BookType();
				type.setDays(rs.getString("days"));
				list.add(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//添加图书类别
	public static int InsertBookType(String bookTypeName,String days,Double fk){
		int i=0;
		try{
			String sql="insert into bookType(typeName,days,fk) values('"+bookTypeName+"','"+days+"',"+fk+")";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	//删除图书类别
	public static int DelbookType(String id){
		int i=0;
		try{
			String sql="delete from bookType where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
	}
	Dao.close();
		return i;
	}
	//修改图书类别
	public static int UpdatebookType(String id,String typeName,String days,String fk){
		int i=0;
		try{
			String sql="update bookType set typeName='"+typeName+"',days='"+days+"',fk='"+fk+"' where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	//添加图书信息
	public static int Insertbook(String ISBN,String typeId,String bookname,String writer,String translator,String publisher,Double price){
		int i=0;
		try{
			String sql="insert into bookInfo(ISBN,typeId,bookname,writer,translator,publisher,price) values('"+ISBN+"','"+typeId+"','"+bookname+"','"+writer+"','"+translator+"','"+publisher+"',"+price+")";

			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		Dao.close();
		return i;
	}
	//查询图书信息
	public static List selectBookInfo() {
		List list=new ArrayList();
		String sql = "select *  from bookInfo";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeid(rs.getString("typeid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));
				bookinfo.setPrice(rs.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	public static List selectBookInfo(String ISBN) {
		List list=new ArrayList();
		String sql = "select *  from bookInfo where ISBN='"+ISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(rs.getString("ISBN"));
				bookinfo.setTypeid(rs.getString("typeid"));
				bookinfo.setBookname(rs.getString("bookname"));
				bookinfo.setWriter(rs.getString("writer"));
				bookinfo.setTranslator(rs.getString("translator"));
				bookinfo.setPublisher(rs.getString("publisher"));

				bookinfo.setPrice(rs.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//修改图书信息
	public static int Updatebook(String ISBN,String typeId,String bookname,String writer,String translator,String publisher,Double price){
		int i=0;
		try{
			String sql="update bookInfo set ISBN='"+ISBN+"',typeId='"+typeId+"',bookname='"+bookname+"',writer='"+writer+"',translator='"+translator+"',publisher='"+publisher+"',price="+price+" where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
    //删除图书信息
	public static int Delbook(String ISBN){
		int i=0;
		try{
			String sql="delete from bookInfo where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();

		}
		Dao.close();
		return i;
	}
	//添加读者信息
	public static int InsertReader(String name,String sex,String age,String identityCard,String maxNum,String tel,String zj,String zy,String ISBN){
		int i=0;
		try{

			String sql="insert into reader(name,sex,age,identityCard,maxNum,tel,zj,zy,ISBN) values('"+name+"','"+sex+"','"+age+"','"+identityCard+"','"+maxNum+"','"+tel+"','"+zj+"','"+zy+"','"+ISBN+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();

		}
		Dao.close();
		return i;
	}
	public static List selectReader() {
		List list=new ArrayList();
		String sql = "select *  from reader";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));

				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static List selectReader(String readerISBN) {
		List list=new ArrayList();
		String sql = "select *  from reader where ISBN='"+readerISBN+"'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//修改读者信息
	public static int UpdateReader(String name,String sex,String age,String identityCard,String maxNum,String tel,String zj,String zy,String ISBN){
		int i=0;
		try{
			String sql="update reader set name='"+name+"',sex='"+sex+"',age='"+age+"',identityCard='"+identityCard+"',maxNum='"+maxNum+"',tel='"+tel+"',zj='"+zj+"',zy='"+zy+"'where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//删除读者信息
	public static int DelReader(String ISBN){
		int i=0;
		try{
			String sql="delete from reader where ISBN='"+ISBN+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public  static  List selectreaderserch(){
		List list=new ArrayList();
		String sql = "select *  from reader ";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//通过ISBN查询读者信息
	public  static  List selectreadermohu(String ISBN){
		List list=new ArrayList();
		String sql = "select *  from reader where ISBN like '%"+ISBN+"%'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//通过读者姓名搜索读者信息
	public  static  List selectreadermohuname(String name){
		List list=new ArrayList();
		String sql = "select *  from reader where name like '%"+name+"%'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader=new Reader();
				reader.setName(rs.getString("name"));
				reader.setSex(rs.getString("sex"));
				reader.setAge(rs.getString("age"));
				reader.setIdentityCard(rs.getString("identityCard"));
				reader.setMaxNum(rs.getString("maxNum"));
				reader.setTel(rs.getString("tel"));
				reader.setZj(rs.getInt("zj"));
				reader.setZy(rs.getString("zy"));
				reader.setISBN(rs.getString("ISBN"));
				list.add(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
    //图书借阅
	public static int InsertBookBorrow(String bookISBN,String readerISBN,String operatorId,Timestamp borrowDate){
		int i=0;
		try{
			String sql="insert into borrow(bookISBN,readerISBN,operatorId,borrowDate)values('"+bookISBN+"','"+readerISBN+"','"+operatorId+"','"+borrowDate+"')";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//图书归还
	public static List selectBookBack(String readerISBN) {
		List list=new ArrayList();
		String sql = "select a.ISBN as bookISBN, a.bookname, a.typeId ,b.id,b.operatorId, b.borrowDate,  c.name as readerName, c.ISBN as readerISBN from bookInfo a inner join borrow b on a.ISBN = b.bookISBN inner join reader c on b.readerISBN = c.ISBN where (c.ISBN = '"+readerISBN+"' and isback=1)";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Back back=new Back();
				back.setBookISBN(rs.getString("bookISBN"));
				back.setBookname(rs.getString("bookname"));
				back.setTypeId(rs.getInt("typeId"));
				back.setOperatorId(rs.getString("operatorId"));
				back.setBorrowDate(rs.getString("borrowDate"));
				back.setReaderName(rs.getString("readerName"));
				back.setReaderISBN(rs.getString("readerISBN"));
				back.setId(rs.getInt("id"));
				list.add(back);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static int UpdateBookBack(String bookISBN,String readerISBN,int id){//归还图书操作
		int i=0;
		try{
			String sql="update borrow set isback=1 where bookISBN='"+bookISBN+"'and readerISBN='"+readerISBN+"' and id="+id+"";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//图书搜索
	public static List selectbookserch() {
		List list=new ArrayList();
		String sql = "select *  from bookInfo";
		ResultSet s = Dao.executeQuery(sql);
		try {
			while (s.next()) {
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(s.getString("ISBN"));
				bookinfo.setTypeid(s.getString("typeId"));
				bookinfo.setBookname(s.getString("bookName"));
				bookinfo.setWriter(s.getString("writer"));
				bookinfo.setTranslator(s.getString("translator"));
				bookinfo.setPublisher(s.getString("publisher"));
				bookinfo.setPrice(s.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//通过图书名搜索图书信息
	public static List selectbookmohu(String bookname){
		List list=new ArrayList();
		String sql="select * from bookInfo where bookname like '%"+bookname+"%'";
		System.out.print(sql);
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(s.getString("ISBN"));
				bookinfo.setTypeid(s.getString("typeId"));
				bookinfo.setBookname(s.getString("bookName"));
				bookinfo.setWriter(s.getString("writer"));
				bookinfo.setTranslator(s.getString("translator"));
				bookinfo.setPublisher(s.getString("publisher"));
				bookinfo.setPrice(s.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;
	}
    //通过作者搜索图书信息
	public static List selectbookmohuwriter(String writer){
		List list=new ArrayList();
		String sql="select * from bookInfo where writer like '%"+writer+"%'";
		System.out.print(sql);
		ResultSet s=Dao.executeQuery(sql);
		try {
			while(s.next()){
				BookInfo bookinfo=new BookInfo();
				bookinfo.setISBN(s.getString("ISBN"));
				bookinfo.setTypeid(s.getString("typeId"));
				bookinfo.setBookname(s.getString("bookName"));
				bookinfo.setWriter(s.getString("writer"));
				bookinfo.setTranslator(s.getString("translator"));
				bookinfo.setPublisher(s.getString("publisher"));
				bookinfo.setPrice(s.getDouble("price"));
				list.add(bookinfo);
			}
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return list;
	}
	public static int Insertoperator(String name,String sex,int age,String identityCard,String tel,String password){
		int i=0;
		try{
			String sql="insert into operator(name,sex,age,identityCard,tel,password) values('"+name+"','"+sex+"',"+age+",'"+identityCard+"','"+tel+"','"+password+"')";
			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static List selectuser() {
		List list=new ArrayList();
		String sql = "select id,name,sex,age,identityCard,tel,password  from operator where admin=0";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				user user=new user();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSex(rs.getString(3));
				user.setAge(rs.getInt(4));
				user.setIdentityCard(rs.getString(5));
				user.setTel(rs.getString(6));
				user.setPassword(rs.getString(7));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	public static int Deluser(int id){
		int i=0;
		try{
			String sql="delete from operator where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	public static int Updateuser(int id,String name,String sex,int age,String identityCard,String tel,String password){
		int i=0;
		try{
			String sql="update operator set name='"+name+"',sex='"+sex+"',age="+age+",identityCard='"+identityCard+"',tel='"+tel+"',password='"+password+"' where id='"+id+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//修改密码
	public static int Updatepass(String password,String name){
		int i=0;
		try{
			String sql="update operator set password='"+password+"' where name='"+name+"'";
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
}