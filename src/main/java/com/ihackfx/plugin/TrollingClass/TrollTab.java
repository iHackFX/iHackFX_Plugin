package com.ihackfx.plugin.TrollingClass;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TrollTab implements org.bukkit.command.TabCompleter {

    List<String> types = new ArrayList<String>();

    @Override
    public List<String> onTabComplete (CommandSender sender, Command cmd, String label, String[] args){
        if (types.isEmpty()) {
            types.add("push");
            types.add("vomit");
            types.add("invtroll");
        }

        List<String> result = new ArrayList<String>();
        if(args.length == 1){
            for (String a: types){
                if (a.toLowerCase(Locale.ROOT).startsWith(args[0].toLowerCase(Locale.ROOT)))
                    result.add(a);
            }
            return result;
        }
        return null;
    }
}
