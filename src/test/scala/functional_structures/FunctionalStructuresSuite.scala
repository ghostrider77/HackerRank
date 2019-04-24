package functional_structures

import org.scalatest.{FreeSpec, Matchers}

class FunctionalStructuresSuite extends FreeSpec with Matchers {

  "SwapNodes" - {
    import SwapNodes.{BinaryTree, buildBinaryTree, performTraversals}

    "should swap the subtrees of a binary tree at depth k, 2k, ... etc" - {
      "test case 1" in {
        val nrNodes: Int = 3
        val childIndices: Iterator[String] = Iterator("2 3", "-1 -1", "-1 -1")
        val swaps: List[Int] = List(1, 1)
        val tree: BinaryTree = buildBinaryTree(childIndices, nrNodes)
        val traversals: List[List[Int]] = performTraversals(tree, swaps)
        traversals shouldEqual List(List(3, 1, 2), List(2, 1, 3))
      }

      "test case 2" in {
        val nrNodes: Int = 5
        val childIndices: Iterator[String] = Iterator("2 3", "-1 4", "-1 5", "-1 -1", "-1 -1")
        val swaps: List[Int] = List(2)
        val tree: BinaryTree = buildBinaryTree(childIndices, nrNodes)
        val traversals: List[List[Int]] = performTraversals(tree, swaps)
        traversals shouldEqual List(List(4, 2, 1, 5, 3))
      }

      "test case 3" in {
        val nrNodes: Int = 11
        val childIndices: Iterator[String] =
          Iterator("2 3", "4 -1", "5 -1", "6 -1", "7 8", "-1 9", "-1 -1", "10 11", "-1 -1", "-1 -1", "-1 -1")
        val swaps: List[Int] = List(2, 4)
        val tree: BinaryTree = buildBinaryTree(childIndices, nrNodes)
        val traversals: List[List[Int]] = performTraversals(tree, swaps)
        traversals shouldEqual List(List(2, 9, 6, 4, 1, 3, 7, 5, 11, 8, 10), List(2, 6, 9, 4, 1, 3, 7, 5, 10, 8, 11))
      }
    }
  }

  "ValidBinarySearchTree" - {
    import ValidBST.isValidBST

    "should determine if a given list is a preorder traversal of a valid binary search tree" in {
      val input: List[List[Int]] =
        List(
          List(1, 2, 3),
          List(2, 1, 3),
          List(3, 2, 1, 5, 4, 6),
          List(1, 3, 4, 2),
          List(3, 4, 5, 1, 2)
        )
      input.map(isValidBST) shouldEqual List(true, true, true, false, false)
    }
  }

  "ListAndGCD" - {
    import functional_structures.ListAndGCD.{CanonicalForm, readCanonicalForm, calcGCD}

    "should calculate the greatest common divisor of elements of a list given in their canonical forms" - {
      "test case 1" in {
        val n: Int = 2
        val lines: List[String] = List("7 2", "2 2 7 1")
        val numbers: List[CanonicalForm] = lines.map(readCanonicalForm)
        calcGCD(numbers) shouldEqual Map(7 -> 1)
      }

      "test case 2" in {
        val n: Int = 4
        val lines: List[String] = List("2 2 3 2 5 3", "3 2 5 3 11 1", "2 2 3 3 5 4 7 6 19 18", "3 10 5 15")
        val numbers: List[CanonicalForm] = lines.map(readCanonicalForm)
        calcGCD(numbers) shouldEqual Map(3 -> 2, 5 -> 3)
      }
    }
  }

  "SubstringSearching" - {
    import functional_structures.SubstringSearching.{TextData, doesPatternOccurAsSubstring}

    "should detect if pattern appears as substring in the given text" in {
      val input: List[TextData] =
        List(
          TextData(text = "abcdef", pattern = "def"),
          TextData(text = "computer", pattern = "muter"),
          TextData(text = "stringmatchingmat", pattern = "ingmat"),
          TextData(text = "videobox", pattern = "videobox")
        )
      input.map(doesPatternOccurAsSubstring) shouldEqual List(true, false, true, true)
    }
  }

  "MatrixRotation" - {
    import functional_structures.MatrixRotation.{Matrix, calcRotatedMatrix}

    "should rotate the matrices in counterclockwise direction" - {
      "test case 1" in {
        val nRows: Int = 4
        val nCols: Int = 4
        val r: Int = 1
        val matrix: Matrix =
          Vector(Vector(1, 2, 3, 4), Vector(5, 6, 7, 8), Vector(9, 10, 11, 12), Vector(13, 14, 15, 16))
        calcRotatedMatrix(matrix, nRows, nCols, r) shouldEqual
          Vector(Vector(2, 3, 4, 8), Vector(1, 7, 11, 12), Vector(5, 6, 10, 16), Vector(9, 13, 14, 15))
      }

      "test case 2" in {
        val nRows: Int = 4
        val nCols: Int = 4
        val r: Int = 2
        val matrix: Matrix =
          Vector(Vector(1, 2, 3, 4), Vector(5, 6, 7, 8), Vector(9, 10, 11, 12), Vector(13, 14, 15, 16))
        calcRotatedMatrix(matrix, nRows, nCols, r) shouldEqual
          Vector(Vector(3, 4, 8, 12), Vector(2, 11, 10, 16), Vector(1, 7, 6, 15), Vector(5, 9, 13, 14))
      }

      "test case 3" in {
        val nRows: Int = 5
        val nCols: Int = 4
        val r: Int = 7
        val matrix: Matrix =
          Vector(
            Vector(1, 2, 3, 4),
            Vector(7, 8, 9, 10),
            Vector(13, 14, 15, 16),
            Vector(19, 20, 21, 22),
            Vector(25, 26, 27, 28)
          )
        calcRotatedMatrix(matrix, nRows, nCols, r) shouldEqual Vector(Vector(28, 27, 26, 25), Vector(22, 9, 15, 19),
            Vector(16, 8, 21, 13), Vector(10, 14, 20, 7), Vector(4, 3, 2, 1))
      }

      "test case 4" in {
        val nRows: Int = 2
        val nCols: Int = 2
        val r: Int = 3
        val matrix: Matrix = Vector(Vector(1, 1), Vector(1, 1))
        calcRotatedMatrix(matrix, nRows, nCols, r) shouldEqual Vector(Vector(1, 1), Vector(1, 1))
      }
    }
  }
}
