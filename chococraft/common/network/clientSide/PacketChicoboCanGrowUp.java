// <copyright file="PacketChicoboCanGrowUp.java">
// Copyright (c) 2012 All Right Reserved, http://chococraft.arno-saxena.de/
//
// THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
// KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
// PARTICULAR PURPOSE.
//
// </copyright>
// <author>Arno Saxena</author>
// <email>al-s@gmx.de</email>
// <date>2012-10-25</date>
// <summary>Network Packet wrappers sending packets for chicobo grow up status</summary>


package chococraft.common.network.clientSide;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.network.Player;

import chococraft.common.entities.EntityAnimalChocobo;
import chococraft.common.entities.EntityChicobo;

public class PacketChicoboCanGrowUp extends PacketChocoboClient
{
	public PacketChicoboCanGrowUp(EntityChicobo chicobo)
	{
		super();
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try
		{
			outputStream.writeInt(PID_CHIGROWUP);			
			outputStream.writeInt(chicobo.entityId);
			outputStream.writeBoolean(chicobo.isCanGrowUp());
			outputStream.writeBoolean(chicobo.growUp);
			outputStream.writeInt(chicobo.worldObj.provider.dimensionId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}		

		this.packet.data = bos.toByteArray();
		this.packet.length = bos.size();
	}

	public static void handleUpdate(DataInputStream inputStream, Player player)
	{
		if (Side.CLIENT == FMLCommonHandler.instance().getEffectiveSide())
		{
			try
			{
				int entityId = inputStream.readInt();
				boolean canGrowUp = inputStream.readBoolean();
				boolean growUp = inputStream.readBoolean();
				int dimension = inputStream.readInt();

				EntityAnimalChocobo chicobo = getChocoboByID(entityId, dimension);
				if(null != chicobo && chicobo instanceof EntityChicobo)
				{
					((EntityChicobo)chicobo).setCanGrowUp(canGrowUp);
					((EntityChicobo)chicobo).growUp = growUp;
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
				return;
			}
		}
	}
}
