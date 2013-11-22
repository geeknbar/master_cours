<?xml version="1.0"  encoding="ISO-8859-1" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" />

<xsl:template match="/">
<xsl:text>
</xsl:text>
<xsl:text>/*</xsl:text> <xsl:apply-templates select="//comment" /><xsl:text>*/</xsl:text>
<xsl:text>
</xsl:text>
<xsl:apply-templates select="//élément" />
</xsl:template>

<xsl:template match="comment">
<xsl:value-of select="text()" />
</xsl:template>

<xsl:template match="élément">
<xsl:text>
</xsl:text>
		    				<xsl:value-of select="./@classe" />
		    				<xsl:value-of select="./@nom" />
<xsl:text> { </xsl:text>
		    				<xsl:apply-templates select="./style" />
<xsl:text> } </xsl:text>
</xsl:template>

<xsl:template match="style">
<xsl:value-of select="propriété/text()" /><xsl:text> : </xsl:text>
<xsl:value-of select="valeur/text()" />
</xsl:template>
</xsl:stylesheet>
