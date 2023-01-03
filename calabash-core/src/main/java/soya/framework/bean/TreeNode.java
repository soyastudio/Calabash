package soya.framework.bean;

import java.util.List;

public interface TreeNode<T> {

    TreeNode<T> getParent();

    List<TreeNode<T>> getChildren();

    String getName();

    String getPath();

    T getData();

}
