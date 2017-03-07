package com.ardublock.translator.block.makeblock;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class MeLineFollower extends TranslatorBlock {

	public MeLineFollower(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException {
		translator.addHeaderFile("SoftwareSerial.h");
		translator.addHeaderFile("Wire.h");
		translator.addHeaderFile("MeMCore.h");

		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String ret = "MeLineFollower lineFollower" + translatorBlock.toCode() + "(PORT_" + translatorBlock.toCode() + ");";
		translator.addDefinitionCommand(ret);
		return "lineFollower" + translatorBlock.toCode() + ".readSensors()";
	}

}
