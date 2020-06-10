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
#mc.setBlocks(tx,ty-20,tz,tx+800,ty+20,tz+800,block.AIR)
#mc.setBlocks(tx,ty+100,tz,tx+100,ty+200,tz+100,174)

#stone base
mc.setBlocks(tx,ty-9,tz,tx+100,ty,tz+100,block.STONE)

#create tower

def createTower(x, y, z, w=11, d=11, h=16):
    mc.setBlocks(x,y,z,x+w-1,y+h-1,z+d-1,block.STONE)
                 
    for a in range (0,w+1):
        if a % 2 == 0:
            mc.setBlock(a + x, y + h, z, block.STONE)
            mc.setBlock(a + x, y + h, z + d-1, block.STONE)
    
    for a in range (0,d+1):
        if a % 2 == 0:
            mc.setBlock(x, y + h, a + z, block.STONE)
            mc.setBlock(x + w-1, y + h,a + z, block.STONE)

    # create a air layer below
    mc.setBlocks(x+1, y + h-1, z + 1, x + w-2, y + h-1, z + d-2, block.AIR)


#end createTower

createTower(tx,ty,tz)
createTower(tx + 90,ty,tz)
createTower(tx,ty,tz + 90)
createTower(tx + 90,ty,tz + 90)
mc.setBlock(tx, ty, tz, block.ICE)

#create outer wall
mc.setBlocks(tx+ 10, ty + 15, tz + 5, tx + 90, ty, tz + 5, block.STONE)
mc.setBlocks(tx + 6, ty + 14, tz + 10, tx + 6, ty, tz + 94, block.STONE)
mc.setBlocks(tx + 11, ty + 14, tz + 95, tx + 95, ty, tz + 95, block.STONE)
mc.setBlocks(tx + 95, ty + 14, tz + 10, tx + 95, ty, tz + 94, block.STONE)

#roof
mc.setBlocks(tx+4,ty+10,tz+5,tx+95,ty+11,tz+95,block.STONE)


# tower in the middle (wider and shorter)
w = 41
d = 41
h = 13
createTower(tx+30,ty+12,tz+30,w,d,h)

#create very middle tower

#createTower(tx+39,ty+24,tz+39)
