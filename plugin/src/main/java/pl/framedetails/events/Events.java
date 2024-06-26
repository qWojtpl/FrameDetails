package pl.framedetails.events;

import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onClickFrame(EntityDamageByEntityEvent event) {
        if(event.isCancelled()) {
            return;
        }
        if(!(event.getDamager() instanceof Player)) {
            return;
        }
        if(!(event.getEntity() instanceof ItemFrame)) {
            return;
        }
        Player p = (Player) event.getDamager();
        if(p.isSneaking()) {
            return;
        }
        ItemFrame frame = (ItemFrame) event.getEntity();
        ItemStack itemStack = frame.getItem();
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null) {
            return;
        }
        String nbt = itemStack.getItemMeta().getAsString();
        Item contents = new Item(itemStack.getType().getKey().toString(), itemStack.getAmount(), ItemTag.ofNbt(nbt));
        TextComponent component = new TextComponent();
        component.setText(
                (!meta.getDisplayName().isEmpty() ? meta.getDisplayName() : itemStack.getType().name())  +
                (meta.getLore() != null ? "\n" + String.join("\n", meta.getLore()) : "")
        );
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, contents));
        p.spigot().sendMessage(component);
        event.setCancelled(true);
    }

}
