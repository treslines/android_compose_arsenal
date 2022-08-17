package br.com.progdeelite.compose

import org.junit.Test

import org.junit.Assert.*

/**
 * Example Big O = O(n) + Memoization = 531 + 369.6 = 900.6 Vezes mais rápido
 *
 */
class ExampleUnitTest {

    private enum class Guaranty { TWO, THREE, FOUR }
    private val guarantedPermutations = mutableListOf<Array<Int>>()
    private val guaranty = Guaranty.FOUR
    private fun computeGuarantedPermutations(permutation:Array<Int>){
        if(guarantedPermutations.isEmpty()){
            memoize(permutation, guaranty)
            guarantedPermutations.add(permutation)
        } else {
            if(!hasMemoized(permutation, guaranty)){
                memoize(permutation, guaranty)
                guarantedPermutations.add(permutation)
            }
        }
    }

    private val memoization = mutableMapOf<String,Boolean>()
    private fun hasMemoized(a: Array<Int>, guaranty: Guaranty = Guaranty.FOUR): Boolean {
        when(guaranty) {
            Guaranty.FOUR -> {
                if(memoization["${a[0]}${a[1]}${a[2]}${a[3]}"] == true) return true
                if(memoization["${a[0]}${a[1]}${a[2]}${a[4]}"] == true) return true
                if(memoization["${a[0]}${a[1]}${a[2]}${a[5]}"] == true) return true
                if(memoization["${a[0]}${a[1]}${a[3]}${a[4]}"] == true) return true
                if(memoization["${a[0]}${a[1]}${a[3]}${a[5]}"] == true) return true
                if(memoization["${a[0]}${a[1]}${a[4]}${a[5]}"] == true) return true
                if(memoization["${a[0]}${a[2]}${a[3]}${a[4]}"] == true) return true
                if(memoization["${a[0]}${a[2]}${a[3]}${a[5]}"] == true) return true
                if(memoization["${a[0]}${a[2]}${a[4]}${a[5]}"] == true) return true
                if(memoization["${a[0]}${a[3]}${a[4]}${a[5]}"] == true) return true
                if(memoization["${a[1]}${a[2]}${a[3]}${a[4]}"] == true) return true
                if(memoization["${a[1]}${a[2]}${a[3]}${a[5]}"] == true) return true
                if(memoization["${a[1]}${a[2]}${a[4]}${a[5]}"] == true) return true
                if(memoization["${a[1]}${a[3]}${a[4]}${a[5]}"] == true) return true
                if(memoization["${a[2]}${a[3]}${a[4]}${a[5]}"] == true) return true
                return false
            }
            Guaranty.THREE -> {
                if(memoization["${a[0]}${a[1]}${a[2]}"] == true) return true
                if(memoization["${a[0]}${a[1]}${a[3]}"] == true) return true
                if(memoization["${a[0]}${a[1]}${a[4]}"] == true) return true
                if(memoization["${a[0]}${a[1]}${a[5]}"] == true) return true
                if(memoization["${a[0]}${a[2]}${a[3]}"] == true) return true
                if(memoization["${a[0]}${a[2]}${a[4]}"] == true) return true
                if(memoization["${a[0]}${a[2]}${a[5]}"] == true) return true
                if(memoization["${a[0]}${a[3]}${a[4]}"] == true) return true
                if(memoization["${a[0]}${a[3]}${a[5]}"] == true) return true
                if(memoization["${a[0]}${a[4]}${a[5]}"] == true) return true
                if(memoization["${a[1]}${a[2]}${a[3]}"] == true) return true
                if(memoization["${a[1]}${a[2]}${a[4]}"] == true) return true
                if(memoization["${a[1]}${a[2]}${a[5]}"] == true) return true
                if(memoization["${a[1]}${a[3]}${a[4]}"] == true) return true
                if(memoization["${a[1]}${a[3]}${a[5]}"] == true) return true
                if(memoization["${a[1]}${a[4]}${a[5]}"] == true) return true
                if(memoization["${a[2]}${a[3]}${a[4]}"] == true) return true
                if(memoization["${a[2]}${a[3]}${a[5]}"] == true) return true
                if(memoization["${a[2]}${a[4]}${a[5]}"] == true) return true
                if(memoization["${a[3]}${a[4]}${a[5]}"] == true) return true
                return false
            }
            Guaranty.TWO -> {
                if(memoization["${a[0]}${a[1]}"] == true) return true
                if(memoization["${a[0]}${a[2]}"] == true) return true
                if(memoization["${a[0]}${a[3]}"] == true) return true
                if(memoization["${a[0]}${a[4]}"] == true) return true
                if(memoization["${a[0]}${a[5]}"] == true) return true
                if(memoization["${a[1]}${a[2]}"] == true) return true
                if(memoization["${a[1]}${a[3]}"] == true) return true
                if(memoization["${a[1]}${a[4]}"] == true) return true
                if(memoization["${a[1]}${a[5]}"] == true) return true
                if(memoization["${a[2]}${a[3]}"] == true) return true
                if(memoization["${a[2]}${a[4]}"] == true) return true
                if(memoization["${a[2]}${a[5]}"] == true) return true
                if(memoization["${a[3]}${a[4]}"] == true) return true
                if(memoization["${a[3]}${a[5]}"] == true) return true
                if(memoization["${a[4]}${a[5]}"] == true) return true
                return false
            }
        }
    }

    private fun memoize(a: Array<Int>, guaranty: Guaranty = Guaranty.FOUR){
        when(guaranty){
            Guaranty.FOUR -> {
                if(!memoization.containsKey("${a[0]}${a[1]}${a[2]}${a[3]}")) { memoization["${a[0]}${a[1]}${a[2]}${a[3]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[1]}${a[2]}${a[4]}")) { memoization["${a[0]}${a[1]}${a[2]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[1]}${a[2]}${a[5]}")) { memoization["${a[0]}${a[1]}${a[2]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[1]}${a[3]}${a[4]}")) { memoization["${a[0]}${a[1]}${a[3]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[1]}${a[3]}${a[5]}")) { memoization["${a[0]}${a[1]}${a[3]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[1]}${a[4]}${a[5]}")) { memoization["${a[0]}${a[1]}${a[4]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[2]}${a[3]}${a[4]}")) { memoization["${a[0]}${a[2]}${a[3]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[2]}${a[3]}${a[5]}")) { memoization["${a[0]}${a[2]}${a[3]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[2]}${a[4]}${a[5]}")) { memoization["${a[0]}${a[2]}${a[4]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[3]}${a[4]}${a[5]}")) { memoization["${a[0]}${a[3]}${a[4]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[2]}${a[3]}${a[4]}")) { memoization["${a[1]}${a[2]}${a[3]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[2]}${a[3]}${a[5]}")) { memoization["${a[1]}${a[2]}${a[3]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[2]}${a[4]}${a[5]}")) { memoization["${a[1]}${a[2]}${a[4]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[3]}${a[4]}${a[5]}")) { memoization["${a[1]}${a[3]}${a[4]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[2]}${a[3]}${a[4]}${a[5]}")) { memoization["${a[2]}${a[3]}${a[4]}${a[5]}"] = true }
            }
            Guaranty.THREE -> {
                if(!memoization.containsKey("${a[0]}${a[1]}${a[2]}")) { memoization["${a[0]}${a[1]}${a[2]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[1]}${a[3]}")) { memoization["${a[0]}${a[1]}${a[3]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[1]}${a[4]}")) { memoization["${a[0]}${a[1]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[1]}${a[5]}")) { memoization["${a[0]}${a[1]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[2]}${a[3]}")) { memoization["${a[0]}${a[2]}${a[3]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[2]}${a[4]}")) { memoization["${a[0]}${a[2]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[2]}${a[5]}")) { memoization["${a[0]}${a[2]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[3]}${a[4]}")) { memoization["${a[0]}${a[3]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[3]}${a[5]}")) { memoization["${a[0]}${a[3]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[4]}${a[5]}")) { memoization["${a[0]}${a[4]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[2]}${a[3]}")) { memoization["${a[1]}${a[2]}${a[3]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[2]}${a[4]}")) { memoization["${a[1]}${a[2]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[2]}${a[5]}")) { memoization["${a[1]}${a[2]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[3]}${a[4]}")) { memoization["${a[1]}${a[3]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[3]}${a[5]}")) { memoization["${a[1]}${a[3]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[4]}${a[5]}")) { memoization["${a[1]}${a[4]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[2]}${a[3]}${a[4]}")) { memoization["${a[2]}${a[3]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[2]}${a[3]}${a[5]}")) { memoization["${a[2]}${a[3]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[2]}${a[4]}${a[5]}")) { memoization["${a[2]}${a[4]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[3]}${a[4]}${a[5]}")) { memoization["${a[3]}${a[4]}${a[5]}"] = true }
            }
            Guaranty.TWO -> {
                if(!memoization.containsKey("${a[0]}${a[1]}")) { memoization["${a[0]}${a[1]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[2]}")) { memoization["${a[0]}${a[2]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[3]}")) { memoization["${a[0]}${a[3]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[4]}")) { memoization["${a[0]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[0]}${a[5]}")) { memoization["${a[0]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[2]}")) { memoization["${a[1]}${a[2]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[3]}")) { memoization["${a[1]}${a[3]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[4]}")) { memoization["${a[1]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[1]}${a[5]}")) { memoization["${a[1]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[2]}${a[3]}")) { memoization["${a[2]}${a[3]}"] = true }
                if(!memoization.containsKey("${a[2]}${a[4]}")) { memoization["${a[2]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[2]}${a[5]}")) { memoization["${a[2]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[3]}${a[4]}")) { memoization["${a[3]}${a[4]}"] = true }
                if(!memoization.containsKey("${a[3]}${a[5]}")) { memoization["${a[3]}${a[5]}"] = true }
                if(!memoization.containsKey("${a[4]}${a[5]}")) { memoization["${a[4]}${a[5]}"] = true }
            }
        }

    }

    private var validationHits = 0
    private fun validateComputedGuarantedPermutations(permutation:Array<Int>){
        if(hasMemoized(permutation, guaranty)){
            validationHits++
        }
    }

    @Test
    fun permuteWithMemoization() {

        val permutationSize = 6
        val permutationSpace = 60
        val bigO = BigO()
        computeUniquePermutations(
            permutationProcessor = ::computeGuarantedPermutations,
            permutationSize = permutationSize,
            permutationSpace = permutationSpace,
            bigO = bigO
        )

        println(bigO.count)
        println(guarantedPermutations.size)
        var aString = ""
        guarantedPermutations.forEach { outt ->
            outt.forEach { inn ->
                aString = "$aString$inn, "
            }
            println(aString)
            aString = ""
        }

        computeUniquePermutations(
            permutationProcessor = ::validateComputedGuarantedPermutations,
            permutationSize = permutationSize,
            permutationSpace = permutationSpace
        )
        println("hits: $validationHits")
    }

    private fun computeUniquePermutations(
        permutationProcessor: (permutation:Array<Int>)->Unit,
        permutationSize: Int = 6,
        permutationSpace:Int = 60,
        bigO: BigO = BigO()
    ){
        // init perm digits
        val permutationSpaceList = mutableListOf<Int>()
        for(i in 1..permutationSpace){
            permutationSpaceList.add(i)
        }
        var i2: Int; var i3: Int; var i4: Int; var i5: Int
        for(i1 in 0..permutationSpaceList.size-permutationSize){
            i2 = i1+1
            while(i2 <= permutationSpaceList.size-5){
                i3 = i2+1
                while(i3 <= permutationSpaceList.size-4){
                    i4 = i3+1
                    while(i4 <= permutationSpaceList.size-3){
                        i5 = i4+1
                        while(i5 <= permutationSpaceList.size-2){
                            permute(bigO, permutationProcessor, permutationSpaceList, i1, i2, i3, i4, i5, i5+1)
                            i5++
                        }
                        i4++
                    }
                    i3++
                }
                i2++
            }
        }
    }

    private fun permute(bigO: BigO, callback: (perm:Array<Int>)->Unit, root: List<Int>, i1: Int, i2: Int, i3: Int, i4: Int, i5: Int, i6: Int){
        bigO.count++
        if(root.size-1 == i6){
            callback(arrayOf(root[i1],root[i2],root[i3],root[i4],root[i5],root[i6]))
        } else {
            callback(arrayOf(root[i1],root[i2],root[i3],root[i4],root[i5],root[i6]))
            permute(bigO, callback, root, i1, i2 ,i3, i4, i5,i6 + 1)
        }
    }

    data class BigO(var count: Long = 0)

}