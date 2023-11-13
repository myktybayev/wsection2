package kz.project.section2.ui.skills;
import java.util.List;

public class SkillType {
    String skillType;
    List<SkillItem> skillItems;

    public SkillType(String skillType, List<SkillItem> skillItems) {
        this.skillType = skillType;
        this.skillItems = skillItems;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public List<SkillItem> getSkillItems() {
        return skillItems;
    }

    public void setSkillItems(List<SkillItem> skillItems) {
        this.skillItems = skillItems;
    }
}
