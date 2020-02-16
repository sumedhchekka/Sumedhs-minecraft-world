from mcpi.minecraft import Minecraft
import mcpi.block as block
mc = Minecraft.create()
pos = mc.player.getPos()
tx = 203
ty = 77
tz = 244
#blockType.Packed Ice ==> 174
#blockType.Air ==> 

mc.postToChat("Welcome to the world")

#clear the area
mc.setBlocks(tx,ty,tz,tx+100,ty+100,tz+100,block.AIR)

#stone base
mc.setBlocks(tx,ty-10,tz,tx+100,ty,tz+100,block.STONE)

#create tower

def createTower(x, y, z):
    mc.setBlocks(x,y,z,x+10,y+15,z+10,block.STONE)
                 
    for a in range (0,12):
        if a % 2 == 0:
            mc.setBlock(a + x, y + 16, z, block.STONE)
            mc.setBlock(a + x, y + 16, z + 10, block.STONE)
    
    for a in range (0,12):
        if a % 2 == 0:
            mc.setBlock(x, y + 16, a + z, block.STONE)
            mc.setBlock(x + 10, y + 16,a + z, block.STONE)
# create a air layer bellow
    mc.setBlocks(x+1, y + 15, z + 1, x + 9, y + 15, z+ 9, block.AIR)


#end createTower

createTower(tx,ty,tz)


