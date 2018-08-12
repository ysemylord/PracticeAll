//必须要写明package,然后在使用的地方才能导入
package test;
import test.IOnNumArrived;
interface IAddManager{

    int add(int a,int b);
    void registerOnNumArrived(IOnNumArrived iOnNumArrived);
    void unRegisterOnNumArrived(IOnNumArrived iOnNumArrived);
}