package com.briup.iocore;

import java.io.Serializable;

/**
 * 对象序列化（对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种二进制流保存到磁盘上，通过网络将这种二进制流保存到另一个网络节点，其它程序一旦获取这种二进制流，无论是从磁盘中获取还是从网络中获取）都可以将
 * 这种二进制流恢复成原来的Java对象
 * 目标：对象序列化的目标是将对象保存在磁盘中，或允许在网络中传输对象
 * 序列化机制：允许将实现序列化的java对象转换成字节序列，序列化机制可以使得对象脱离程序的运行而独立存在。对象的序列化指将一个Java对象写入IO流，与此对应的是，对象的反序列化则指从IO流中恢复该Java对象；如果需要让某个对象
 * 支持序列化机制，则必须让它的类是可序列化的，该类必须实现如下两个接口之一（Serializable接口和Externalizable接口），Serializable接口是一个标记接口，实现该接口无需实现任何方法，它只是表明该类的实例是可序列化的，所有能在网络上传输的
 * 对象都是可序列化的，否则程序将会出现异常，比如RMI（远程方法调用，是JavaEE的基础）过程中的参数和返回值，所有需要保存到磁盘中的对象的类都必须是可序列化，比如Web应用中需要保存到HttpSession或ServletContext属性的Java对象;
 * 因为序列化是RMI过程的参数和返回值都必须实现的机制，而RMI又是JavaEE技术的基础，----所有的分布式应用常常需要跨平台，跨网络，所以要求所有传递的参数，返回值必须实现序列化。通常建议:程序创建的每个JavaBean类都实现Serializable.
 * 1.使用对象流实现序列化
 *   步骤a:创建一个ObjectOutputStream，这个输出流是一个处理流，所以必须建立在其他节点流的基础之上
 *   步骤b:调用ObjectOutputStream对象的writeObject()方法输出可序列化对象
 * 2.如果希望从二进制流中恢复Java对象，则需要使用反序列化
 *   步骤a:创建一个ObjectInputStream输入流，这个输入流是一个处理流，所以必须建立在其它节点流的基础之上
 *   步骤b:调用ObjectOutputStream对象的readObject()方法读取流中的对象，该方法返回一个Object类型的Java对象，如果程序知道该java对象的类型,则可以将该对象强制转换成其真实类型
 * 
 * @author WE
 *
 */
public class ObjectSequenceBean implements Serializable{
	private String name;
	private int age;
	//注意此处没有提供无参数的构造器
	public ObjectSequenceBean(String name,int age){
		System.out.println("有参数的构造器");
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	



}
