/*
 * Copyright (c) 2002-2016 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.commandline.arguments;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArgumentsTest
{

    private Arguments builder;

    @Before
    public void setup()
    {
        builder = new Arguments();
    }

    @Test
    public void withDatabaseUsage() throws Exception
    {
        Assert.assertEquals( "[--database=<name>]", builder.withDatabase().usage() );
    }

    @Test
    public void withDatabaseDescription() throws Exception
    {
        Assert.assertEquals( "How to use\n\noptions:\n" +
                        "  --database=<name>   Name of database. [default:graph.db]",
                builder.withDatabase().description( "How to use" ) );
    }

    @Test
    public void withDatabaseToUsage() throws Exception
    {
        Assert.assertEquals( "[--database=<name>] --to=<destination-path>", builder.withDatabase().withTo(
                "Destination file." ).usage() );
    }

    @Test
    public void withDatabaseToDescription() throws Exception
    {
        Assert.assertEquals( "How to use\n\noptions:\n" +
                        "  --database=<name>         Name of database. [default:graph.db]\n" +
                        "  --to=<destination-path>   Destination file.",
                builder.withDatabase().withTo( "Destination file." ).description( "How to use" ) );
    }

    @Test
    public void withDatabaseToMultilineDescription() throws Exception
    {
        Assert.assertEquals( "How to use\n\noptions:\n" +
                        "  --database=<name>         Name of database. [default:graph.db]\n" +
                        "  --to=<destination-path>   This is a long string which should wrap on right\n" +
                        "                            col.",
                builder.withDatabase()
                        .withTo( "This is a long string which should wrap on right col." )
                        .description( "How to use" ) );
    }

    @Test
    public void longNamesTriggerNewLineFormatting() throws Exception
    {
        Assert.assertEquals( "How to use\n\noptions:\n" +
                        "  --database=<name>\n" +
                        "      Name of database. [default:graph.db]\n" +
                        "  --to=<destination-path>\n" +
                        "      This is a long string which should not wrap on right col.\n" +
                        "  --loooooooooooooong-variable-name=<loooooooooooooong-variable-value>\n" +
                        "      This is also a long string which should be printed on a new line because\n" +
                        "      of long names.",
                builder.withDatabase()
                        .withTo( "This is a long string which should not wrap on right col." )
                        .withArgument( new MandatoryNamedArg( "loooooooooooooong-variable-name",
                                "loooooooooooooong-variable-value",
                                "This is also a long string which should be printed on a new line because of long " +
                                        "names.") )
                        .description( "How to use" ) );
    }
}
