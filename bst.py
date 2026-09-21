class BSTNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class BinarySearchTree:
    def __init__(self):
        self.root = None
    
    def insert_recursive(self, node, val):
        if val < node.val:
            if node.left is None:
                node.left = BSTNode(val)
            else:
                self.insert_recursive(node.left, val)
        elif val > node.val:
            if node.right is None:
                node.right = BSTNode(val)
            else:
                self.insert_recursive(node.right, val)
    
    def insert(self, val):
        if self.root is None:
            self.root = BSTNode(val)
        else:
            self.insert_recursive(self.root, val)

    def search_recursive(self, node, val):
        if node is None:
            return False
        if node.val == val:
            return node
        elif val < node.val:
            return self.search_recursive(node.left, val)
        else:
            return self.search_recursive(node.right, val)
    
    def search(self, val):
        return self.search_recursive(self.root, val)
    
    def remove(self, val):
        self.root = self.remove_node(val, self.root)
    
    def remove_node(self, val, node):
        if node is None:
            return None
        
        if val < node.val:
            node.left = self.remove_node(val, node.left)
            return node
        elif val > node.val:
            node.right = self.remove_node(val, node.right)
            return node
        if val == node.val:
            if node.left is None:
                return node.right
            elif node.right is None:
                return node.left
            else:
                original = node
                node = node.right
                while node.left:
                    node = node.left
                node.left = original.left
                node.right = self.remove_node(node.val, original.right)
                return node


import time
import random

#Максимальный элемент в массиве
def MaxEl(arr):
    Max = arr[0]
    for el in arr:
        if el > Max:
            Max = el
    return Max

def SecondMax(arr):
    Max = float('-inf')
    Max2 = float('-inf')
    for el in arr:
        if el > Max:
            Max2 = Max
            Max = el
        elif el > Max2 and el < Max:
            Max2 = el
    return Max2
        
elements = [random.randint(1, 1000000) for _ in range(100000)]
array = [19, 14, 53, 3, 15, 26, 59, 23, 55, 54]

'''
start = time.time()
print("Второй по величине элемент:", SecondMax(elements))
finish = time.time()
print("Время для поиска по массиву:", finish - start)
'''
#Максимальный элемент в дереве

def MaxBST(tree):
    tree = tree.root
    while not tree.right is None:
        tree = tree.right
    return tree.val

def SecondMaxBST(tree):
    tree = tree.root
    while not tree.right is None:
        parent = tree
        tree = tree.right
    if tree.left is None:
        return parent.val
    else:
        tree = tree.left
        while not tree.right is None:
            tree = tree.right
        return tree.val


bst = BinarySearchTree()
for el in array:
    bst.insert(el)

def build_balanced_bst(arr):
    tree = BinarySearchTree()
    sorted_arr = sorted(arr)
    
    def build_helper(low, high):
        if low > high:
            return
        
        mid = (low + high) // 2
        tree.insert(sorted_arr[mid])
        
        build_helper(low, mid - 1)
        build_helper(mid + 1, high)

    build_helper(0, len(sorted_arr) - 1)
    
    return tree

array = [19, 14, 53, 3, 15, 26, 59, 23, 55, 54]
balanced_tree = build_balanced_bst(array)

print("Корень сбалансированного дерева:", balanced_tree.root.val)

print(bst.search(19).right.val)

bst.remove(53)

print(bst.search(19).right.val)