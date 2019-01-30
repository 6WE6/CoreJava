package com.briup.generic;


import java.util.ArrayList;
import java.util.List;

/**
 * 泛型：在定义类，接口，方法时使用类型形参，这个类型形参将在声明变量，创建对象，调用方法时动态的指定(即传入实际的类型形参，也可成为类型实参).泛型的设计原则：只要代码在编译时没有出现警告，就不会遇到运行时ClassCastException
 * 1.定义泛型类，接口.当创建带泛型声明的自定义类，为该类定义构造器时，构造器还是原来的类名，不要增加泛型声明.
 * 2.从泛型类派生子类，当创建了带泛型声明的接口、父类之后，可以为该接口创建实现类，或者从父类派生子类，当使用这些接口、父类时不能在包含类型形参，
 * 调用方法时必须为所有的数据形参传入参数值，与调用方法不同的是，使用类，接口时，也可以不为类型形参传入实际的类型参数，如果需要重写父类的方法，返回值与父类FirstGeneric<T>的返回值完全相同，否则报错
 * 3.并不存在泛型类:不管泛型的实际类型参数是什么，它们在运行时总有同样的类，在静态方法，静态初始化块或者静态变量的声明和初始化块中不允许使用类型形参.instanceof运算符不能使用泛型类
 * 4.使用类型通配符：介绍类型通配符前，要注意如果Foo是Bar的一个子类型(子类或者子接口),而G是具有泛型声明的类或接口，G<Foo>并不是G<Bar>的子类型,但对于数组而言，Foo[]依然是Bar[]的子类型。List<?>仅表示它是各种泛型List的父类
 * 并不能把元素加入其中；当使用List<?>时，即表明这个List集合可以是任何泛型List的父类，有时只希望它代表某一类泛型的List的父类，因此要设定类型通配符的上限List<? extends Shape>,同样无法确定这个受限制的通配符的具体类型，不能把Shape
 * 对象或其子类对象加入这个泛型集合；
 * 5.设定类型形参的上限public class Apple<T extends Number>{} 
 * @author WE
 *
 */
public class FirstGeneric<T> {
	//使用T类型形参定义实例变量
	private T info;
	public FirstGeneric(){}
	
	//下面的代码将报错，不能在静态变量声明中使用类型形参
//	static T mess;
	T age;
	public void foo(T msg){}
//	public static void bar(T msg){}
	
	
	//使用T类型形参来定义构造器
	public FirstGeneric(T info){
		this.info = info;
	}
	public void setInfo(T info){
		this.info = info;
	}
	public T getInfo(){
		return this.info;
	}
	
	public static void main(String[] args) {
		//由于传给T形参的是String,所以构造器参数只能是String
		FirstGeneric<String> f1 = new FirstGeneric<>("第一个泛型测试类");
		System.out.println(f1.getInfo());
		//由于传给T形参的是Double,所以构造器参数只能是Double或double
		FirstGeneric<Double> d2 = new FirstGeneric<>(5.67);
		System.out.println(d2.getInfo());
		System.out.println("----------从泛型派生子类以及不存在泛型类-----------");
		
		
		List<String> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();
		//调用getClass方法来比较l1和l2的类是否相等
		System.out.println(l1.getClass() == l2.getClass());
		//程序无法确定c集合中元素的类型，所以不能向其中添加对象
		List<?> c = new ArrayList<String>();
//		c.add(new String("sfd"));
//      System.out.println(c.get(0));

		
	}

}
