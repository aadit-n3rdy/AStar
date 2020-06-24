import processing.core.PApplet
import processing.core.PVector
import java.util.Vector

class NodeSet : Vector<Node>() {
    fun containsWithPos(element: Node?): Int {
        for(i in indices) {
            if(element?.pos == get(i).pos) {
                return i
            }
        }
        return -1
    }

    fun containsWithPos(pos : PVector?) : Int {
        for(i in indices) {
            if(pos == get(i).pos) {
                return i
            }
        }
        return -1
    }
}


fun nodeDist(node1 : Node, node2 : Node) : Float{
    return PApplet.dist(node1.pos.x, node1.pos.y, node2.pos.x, node2.pos.y)
}

class PathFinderApp : PApplet()  {

    private val offset = 100F

    private var openSet = NodeSet()
    private var closedSet = NodeSet()
    private var destination = Node(PVector(800F, 600F))
    private val openColor = color(100F, 200F, 50F)
    private val closedColor = color(200F, 100F, 50F)
    private var foundRoute = false
    private var obstacles = mutableSetOf<PVector>()

    private fun endProgram() {

    }

    override fun settings() {
        size(800, 600)
        destination.pos = PVector(800F, 600F)
        var start = Node(PVector(0F, 0F))
        openSet.add(Node(PVector(0F, 0F)))
        start.h = nodeDist(start, destination)
        start.f = start.g + start.h
        obstacles.add(PVector(5*offset, 5*offset))
        obstacles.add(PVector(4*offset, 5*offset))
        obstacles.add(PVector(5*offset, 4*offset))
        smooth(8)

    }

    override fun draw() {
        strokeWeight(5F)
        if(foundRoute) {
            delay(10000)
            exit()
        }
        background(100F)
        fill(openColor)
        for(openNode in openSet) {
            circle(openNode.pos.x, openNode.pos.y, 50F)
        }
        fill(closedColor)
        for(closedNode in closedSet) {
            circle(closedNode.pos.x, closedNode.pos.y, 50F)
        }
        if (openSet.size > 0) {
            var nodeWithLowest: Int = 0
            for (i in openSet.indices) {
                if (openSet[i].f < openSet[nodeWithLowest].f) {
                    nodeWithLowest = i
                }
            }
            var curNode = openSet[nodeWithLowest]
            closedSet.add(clone(openSet[nodeWithLowest]) as Node)
            openSet.removeAt(nodeWithLowest)

            //For node above
            var successor : Node = clone(curNode) as Node
            successor.pos.y -= offset
            successor.parent = curNode.pos
            if (successor.pos == destination.pos) {
                destination = successor
                foundRoute = true
            }
            var shouldAdd : Boolean
            successor.g = curNode.g + nodeDist(successor, curNode)
            successor.h = nodeDist(successor, destination)
            successor.f = successor.g + successor.h
            var i = 0
            shouldAdd = true
            if (openSet.containsWithPos(successor) >= 0) {
                i = openSet.containsWithPos(successor)
                if (openSet[i].f > successor.f) {
                    openSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            } else if (closedSet.containsWithPos(successor) >= 0) {
                i = closedSet.containsWithPos(successor)
                if (closedSet[i].f > successor.f) {
                    closedSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            }
            if(shouldAdd && !obstacles.contains(successor.pos)) {
                openSet.add(clone(successor) as Node?)
            }

            //For node left
            successor = clone(curNode) as Node
            successor.pos.x -= offset
            successor.parent = curNode.pos
            if (successor.pos == destination.pos) {
                destination = successor
                foundRoute = true
            }
            successor.g = curNode.g + nodeDist(successor, curNode)
            successor.h = nodeDist(successor, destination)
            successor.f = successor.g + successor.h
            i = 0
            shouldAdd = true
            if (openSet.containsWithPos(successor) >= 0) {
                i = openSet.containsWithPos(successor)
                if (openSet[i].f > successor.f) {
                    openSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            } else if (closedSet.containsWithPos(successor) >= 0) {
                i = closedSet.containsWithPos(successor)
                if (closedSet[i].f > successor.f) {
                    closedSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            }
            if(shouldAdd && !obstacles.contains(successor.pos)) {
                openSet.add(clone(successor) as Node?)
            }

            //For node right
            successor = clone(curNode) as Node
            successor.pos.x += offset
            successor.parent = curNode.pos
            if (successor.pos == destination.pos) {
                destination = successor
                foundRoute = true
            }
            successor.g = curNode.g + nodeDist(successor, curNode)
            successor.h = nodeDist(successor, destination)
            successor.f = successor.g + successor.h
            i = 0
            shouldAdd = true
            if (openSet.containsWithPos(successor) >= 0) {
                i = openSet.containsWithPos(successor)
                if (openSet[i].f > successor.f) {
                    openSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            } else if (closedSet.containsWithPos(successor) >= 0) {
                i = closedSet.containsWithPos(successor)
                if (closedSet[i].f > successor.f) {
                    closedSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            }
            if(shouldAdd && !obstacles.contains(successor.pos)) {
                openSet.add(clone(successor) as Node?)
            }

            //For node below
            successor = clone(curNode) as Node
            successor.pos.y += offset
            successor.parent = curNode.pos
            if (successor.pos == destination.pos) {
                destination = successor
                foundRoute = true
            }
            successor.g = curNode.g + nodeDist(successor, curNode)
            successor.h = nodeDist(successor, destination)
            successor.f = successor.g + successor.h
            i = 0
            shouldAdd = true
            if (openSet.containsWithPos(successor) >= 0) {
                i = openSet.containsWithPos(successor)
                if (openSet[i].f > successor.f) {
                    openSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            } else if (closedSet.containsWithPos(successor) >= 0) {
                i = closedSet.containsWithPos(successor)
                if (closedSet[i].f > successor.f) {
                    closedSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            }
            if(shouldAdd && !obstacles.contains(successor.pos)) {
                openSet.add(clone(successor) as Node?)
            }

            //For node up-left
            successor = clone(curNode) as Node
            successor.pos.x -= offset
            successor.pos.y -= offset
            successor.parent = curNode.pos
            if (successor.pos == destination.pos) {
                destination = successor
                foundRoute = true
            }
            successor.g = curNode.g + nodeDist(successor, curNode)
            successor.h = nodeDist(successor, destination)
            successor.f = successor.g + successor.h
            i = 0
            shouldAdd = true
            if (openSet.containsWithPos(successor) >= 0) {
                i = openSet.containsWithPos(successor)
                if (openSet[i].f > successor.f) {
                    openSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            } else if (closedSet.containsWithPos(successor) >= 0) {
                i = closedSet.containsWithPos(successor)
                if (closedSet[i].f > successor.f) {
                    closedSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            }
            if(shouldAdd && !obstacles.contains(successor.pos)) {
                openSet.add(clone(successor) as Node?)
            }

            //For node up-right
            successor = clone(curNode) as Node
            successor.pos.x += offset
            successor.pos.y -= offset
            successor.parent = curNode.pos
            if (successor.pos == destination.pos) {
                destination = successor
                foundRoute = true
            }
            successor.g = curNode.g + nodeDist(successor, curNode)
            successor.h = nodeDist(successor, destination)
            successor.f = successor.g + successor.h
            i = 0
            shouldAdd = true
            if (openSet.containsWithPos(successor) >= 0) {
                i = openSet.containsWithPos(successor)
                if (openSet[i].f > successor.f) {
                    openSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            } else if (closedSet.containsWithPos(successor) >= 0) {
                i = closedSet.containsWithPos(successor)
                if (closedSet[i].f > successor.f) {
                    closedSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            }
            if(shouldAdd && !obstacles.contains(successor.pos)) {
                openSet.add(clone(successor) as Node?)
            }

            //For node down-left
            successor = clone(curNode) as Node
            successor.pos.x -= offset
            successor.pos.y += offset
            successor.parent = curNode.pos
            if (successor.pos == destination.pos) {
                destination = successor
                foundRoute = true
            }
            successor.g = curNode.g + nodeDist(successor, curNode)
            successor.h = nodeDist(successor, destination)
            successor.f = successor.g + successor.h
            i = 0
            shouldAdd = true
            if (openSet.containsWithPos(successor) >= 0) {
                i = openSet.containsWithPos(successor)
                if (openSet[i].f > successor.f) {
                    openSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            } else if (closedSet.containsWithPos(successor) >= 0) {
                i = closedSet.containsWithPos(successor)
                if (closedSet[i].f > successor.f) {
                    closedSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            }
            if(shouldAdd && !obstacles.contains(successor.pos)) {
                openSet.add(clone(successor) as Node?)
            }

            //For node down-right
            successor = clone(curNode) as Node
            successor.pos.x += offset
            successor.pos.y += offset
            successor.parent = curNode.pos
            if (successor.pos == destination.pos) {
                destination = successor
                foundRoute = true
            }
            successor.g = curNode.g + nodeDist(successor, curNode)
            successor.h = nodeDist(successor, destination)
            successor.f = successor.g + successor.h
            i = 0
            shouldAdd = true
            if (openSet.containsWithPos(successor) >= 0) {
                i = openSet.containsWithPos(successor)
                if (openSet[i].f > successor.f) {
                    openSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            } else if (closedSet.containsWithPos(successor) >= 0) {
                i = closedSet.containsWithPos(successor)
                if (closedSet[i].f > successor.f) {
                    closedSet.removeAt(i)
                }
                else {
                    shouldAdd = false
                }
            }
            if(shouldAdd && !obstacles.contains(successor.pos)) {
                openSet.add(clone(successor) as Node?)
            }

        }
        else {
            kotlin.io.println("Could not find any paths")
        }
        for(obstacle in obstacles) {
            fill(0)
            circle(obstacle.x, obstacle.y, 50F)
        }
        if(foundRoute) {
            background(100)
            for(obstacle in obstacles) {
                fill(0)
                circle(obstacle.x, obstacle.y, 50F)
            }
            var curNode = destination
            var prevNode : Node? = null
            while(true) {
                fill(0F, 200F, 50F)

                circle(curNode.pos.x, curNode.pos.y, 50F)
                if(prevNode != null) {
                    line(prevNode.pos.x, prevNode.pos.y, curNode.pos.x, curNode.pos.y)
                }
                if(curNode.pos == PVector(0F, 0F)) {
                    break;
                }
                prevNode = curNode
                curNode = closedSet[closedSet.containsWithPos(curNode.parent)]
            }
        }
    }
}

fun main(args : Array<String>) {
    var applet = PathFinderApp()
    processing.core.PApplet.runSketch(arrayOf("PathFinder App"), applet)
}