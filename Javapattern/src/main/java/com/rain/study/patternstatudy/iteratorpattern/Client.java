package com.rain.study.patternstatudy.iteratorpattern;

/**
 * 迭代器模式
 * 迭代器模式是一种行为型设计模式，它允许您以统一的方式遍历集合中的元素，而无需了解其内部结构。在迭代器模式中，我们定义了一个抽象迭代器接口，其中包含用于遍历集合元素的方法。然后我们可以创建多个具体迭代器类来实现不同的遍历方式。
 *
 * 迭代器模式通常包括以下角色：
 *
 * 抽象迭代器(Iterator)：定义了用于遍历集合元素的方法。
 * 具体迭代器(Concrete Iterator)：实现了抽象迭代器，并且负责实际遍历集合元素。
 * 集合类(Aggregate)：定义了创建迭代器对象的方法，并且可以返回一个迭代器对象。
 * 具体集合类(Concrete Aggregate)：实现了集合类接口，并提供了对集合元素的访问方法。
 * 适用场景
 * 迭代器模式通常用于以下场景：
 *
 * 当需要遍历集合中的元素时，并且希望将其与集合的具体实现分离开来时。
 * 当希望以不同的方式遍历集合中的元素时，例如按顺序、随机等。
 * 当需要对集合元素进行增、删、改操作时，而不想破坏遍历的顺序。
 * 迭代器模式的一个经典例子是，当我们需要遍历不同类型的数据结构时，可以使用迭代器模式。
 * 例如，我们可以定义一个抽象迭代器接口来表示不同类型的数据结构，并实现基本的遍历方法(如next、hasNext等)。
 * 然后我们可以创建多个具体迭代器类来表示不同类型的数据结构，例如链表、数组等，并使用不同的遍历方式来实现这些迭代器。
 * 另外，在Java中，集合类库中的Iterator接口就是迭代器模式的一个应
 */


/**
 * 迭代器接口，定义了迭代器的基本操作
 */
interface Iterator {
    boolean hasNext(); // 是否还有下一个元素
    Object next(); // 返回下一个元素
}

/**
 * 集合接口，定义了集合的基本操作
 */
interface Collection {
    Iterator iterator(); // 返回一个迭代器
    int size(); // 返回集合的大小
}

/**
 * 具体集合实现类
 */
class ConcreteCollection implements Collection {
    private Object[] objs = {"A", "B", "C", "D", "E"};

    @Override
    public Iterator iterator() {
        return new ConcreteIterator(this);
    }

    @Override
    public int size() {
        return objs.length;
    }

    public Object get(int index) {
        return objs[index];
    }
}

/**
 * 具体迭代器实现类
 */
class ConcreteIterator implements Iterator {
    private ConcreteCollection collection;
    private int pos = -1;

    public ConcreteIterator(ConcreteCollection collection) {
        this.collection = collection;
    }

    @Override
    public boolean hasNext() {
        return pos < collection.size() - 1;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            pos++;
            return collection.get(pos);
        }
        return null;
    }
}




public class Client {
    public static void main(String[] args) {

        // 使用迭代器模式遍历集合
        ConcreteCollection collection = new ConcreteCollection();
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
