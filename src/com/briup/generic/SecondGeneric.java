package com.briup.generic;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

/**
 * 泛型方法：在类的方法定义，成员变量定义，接口的方法定义中，类型形参可以被当成普通类型使用.所谓的泛型方法，就是在声明方法时，定义一个或多个类型形参.与普通方法的方法签名多了类型形参的声明.
 * 1.注：下面代码中T代表String类型，但nr是一个Number数组，因为Number既不是String类型，也不是它的子类，所以出现编译错误.与接口、类声明中定义的类型形参不同的是，方法声明中定义的形参只能是在该方法里使用;
 * 与类，接口中使用泛型参数不同的是，方法中的泛型参数无须显示传入实际类型的参数
 * 2.泛型方法和类型通配符的区别：a:类型形参产生的唯一效果是可以在不同的调用点传入不同的实际类型，通配符就是被设计用来支持灵活的子类化的.b:泛型方法允许类型形参被用来表示方法的一个或多个参数之间的类型依赖关系，或者方法返回值与参数之间的类型依赖关系
 * ，如果没有这种类型依赖关系，就不应该使用泛型方法，如果有必要，可以通配符和泛型方法同时用。c:类型通配符既可以在方法签名中定义形参类型，也可以用于定义变量的类型，但泛型方法中的类型形参必须在对应方法中显式声明;例如
 * public interface Collection<E>{
 *     boolean containsAll(Collection<?> c);
 *     boolean addAll(Collection<? extends E> c);
 * }
 * 3.菱形语法与泛型构造器：菱形语法，它允许调用构造器时在构造器后面使用一对尖括号来代表泛型信息，但如果程序显式指定了泛型构造器中声明的类型形参的实际类型，则不可以使用菱形语法。
 * 4.设定通配符的下限：<? super Type>这个通配符表示他必须是Type本身，或是Type的父类
 * 5.注意泛型方法与方法重载，注意Java不支持创建泛型数组
 * @author WE
 *
 */

class Foo{
	public <T> Foo(T t){
		System.out.println(t);
	}
}
class MyClass<E>{
	public <T> MyClass(T t){
		System.out.println("t参数的值为："+t);
	}
}
public class SecondGeneric {
	
	//声明一个泛型方法，该泛型方法中带一个T类型形参
	static <T> void fromArrayToCollection(T[] a,Collection<T> c){
		for(T o : a){
			c.add(o);
		}
	}
	///声明一个泛型方法，该泛型方法中使用类型通配符的上限
	static <T> void test(Collection<? extends T> from , Collection<T> to){
		for(T ele :from){
			to.add(ele);
		}
	}
	//下面的dest集合元素的类型必须与src集合元素的类型相同，或者是其父类
	public static <T> T copy(Collection<? super T> dest ,Collection<T> src){
		T last = null;
		for(T ele : src){
			last = ele;
			dest.add(ele);
		}
		return last;
		
	}
	public static void main(String[] args) {
		System.out.println("-----------泛型方法的声明-------------");
		Object[] oa = new Object[100];
		Collection<Object> co = new ArrayList<>();
		//下面代码中T代表Object类型
		fromArrayToCollection(oa,co);
		String[] str = new String[100];
		Collection<String> cs = new ArrayList<>();
		//下面代码中T代表String类型
		fromArrayToCollection(str,cs);
		//-------------下面代码中T代表Object类型
		fromArrayToCollection(str,co);
		Integer[] it = new Integer[100];
		Float[] fl = new Float[100];
		Number[] nr = new Number[100];
		Collection<Number> cn = new ArrayList<>();
		//------------下面代码中T代表Number类型
		fromArrayToCollection(it, cn);
		fromArrayToCollection(fl, cn);
		fromArrayToCollection(nr, cn);
		//------------下面代码中T代表Object类型
		fromArrayToCollection(nr, co);
		//下面代码中T代表String类型，但nr是一个Number数组，因为Number既不是String类型，也不是它的子类，所以出现编译错误
//		fromArrayToCollection(nr, cs);
		List<Object> ao = new ArrayList<>();
		List<String> as = new ArrayList<>();
		test(as,ao);
		
		System.out.println("----------------泛型构造器-------------");
		//泛型构造器中的T参数为String类型
		new Foo("qeadfzv");
		//显式指定泛型构造器中的T参数为String类型
		new<String> Foo("cfhshj");
		//显式指定泛型构造器中的T参数为String类型，但传给Foo构造器的实参是Double对象，下面代码出错
//		new<String> Foo(12.3);
		
		MyClass<String> mc1 = new MyClass<>(5);
		//显式指定泛型构造器中声明的T形参是Integer类型
		MyClass<String> mc2 = new <Integer> MyClass<String>(5);
		//MyClass类声明的中的E形参是String类型，如果显式指定泛型构造器中声明的T形参是Integer类型，此时就不能使用菱形语法
//		MyClass<String> mc3 = new <Integer> MyClass<>(5);
		System.out.println("-----------------通配符的下限----------------");
		List<Number> ln = new ArrayList<>();
		List<Integer> li = new ArrayList<>();
		li.add(5);
		//此处可准确知道最后一个被复制的元素是Integer类型,与src集合元素的类型相同
		Integer last =copy(ln,li);
		System.out.println(ln);
		
		

		

	}

}
